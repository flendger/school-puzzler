package ru.flendger.school.puzzler.web.service.input;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flendger.school.puzzler.model.service.input.DateTimeService;
import ru.flendger.school.puzzler.model.settings.ApplicationSettingsService;
import ru.flendger.school.puzzler.model.settings.props.KeyExpiredTimeSetting;
import ru.flendger.school.puzzler.web.dto.LessonKeyRequest;
import ru.flendger.school.puzzler.web.dto.LessonKeyResponse;
import ru.flendger.school.puzzler.web.entity.LessonKey;
import ru.flendger.school.puzzler.web.generator.KeyGenerator;
import ru.flendger.school.puzzler.web.service.output.LessonKeyStorageService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonKeyServiceImpl implements LessonKeyService {
    private final KeyGenerator keyGenerator;
    private final DateTimeService dateTimeService;
    private final LessonKeyStorageService lessonKeyStorageService;
    private final ModelMapper modelMapper;
    private final ApplicationSettingsService applicationSettingsService;

    @Override
    @Transactional
    public LessonKeyResponse generateKey(LessonKeyRequest lessonKeyRequest) {
        KeyExpiredTimeSetting keyExpiredTimeSetting = applicationSettingsService.getSetting(KeyExpiredTimeSetting.class);

        LessonKeyResponse lessonKeyResponse = new LessonKeyResponse(
                keyGenerator.generate(),
                lessonKeyRequest.getLessonId(),
                lessonKeyRequest.getSchoolClassId(),
                dateTimeService.current().plusSeconds(keyExpiredTimeSetting.getValue()));

        LessonKey lessonKey = modelMapper.map(lessonKeyResponse, LessonKey.class);
        lessonKeyStorageService.save(lessonKey);

        return lessonKeyResponse;
    }

    @Override
    public void delete(String key) throws EntityNotFoundException {
        Optional<LessonKey> keyOptional = lessonKeyStorageService.findActive(key, dateTimeService.current());
        if (keyOptional.isEmpty()) {
            throw createActiveKeyNotFoundException(key);
        }

        lessonKeyStorageService.delete(keyOptional.get());
    }

    private EntityNotFoundException createActiveKeyNotFoundException(String key) {
        return new EntityNotFoundException(messageActiveKeyNotFound(key));
    }

    private String messageActiveKeyNotFound(String key) {
        return String.format("Active lesson key %s doesn't exist", key);
    }
}
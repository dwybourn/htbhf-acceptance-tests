package uk.gov.dhsc.htbhf.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uk.gov.dhsc.htbhf.page.PageName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ToggleConfiguration {

    private Map<String, Boolean> toggles;

    public ToggleConfiguration(String featureToggleJson, ObjectMapper objectMapper) {
        toggles = loadToggles(featureToggleJson, objectMapper);
    }

    private Map<String, Boolean> loadToggles(String featureToggleJson, ObjectMapper objectMapper) {
        if (StringUtils.isBlank(featureToggleJson)) {
            log.info("No toggles JSON found in environment variable FEATURE_TOGGLES defaulting to no toggles");
            return new HashMap<>();
        }
        return toggles = readToggleJson(objectMapper, featureToggleJson);
    }

    private Map<String, Boolean> readToggleJson(ObjectMapper objectMapper, String featureToggleJson) {
        try {
            Map<String, Boolean> toggleMap = objectMapper.readValue(featureToggleJson, new TypeReference<Map<String, Boolean>>() {
            });
            log.info("Using toggles: {}", toggleMap);
            return toggleMap;
        } catch (Exception e) {
            log.error("Problem occurred reading in the toggles JSON [" + featureToggleJson + "], defaulting to no toggles", e);
            return new HashMap<>();
        }
    }

    public Map<String, Boolean> getAllToggles() {
        return toggles;
    }

    public boolean isEnabled(String toggle) {
        if (toggles.containsKey(toggle)) {
            return toggles.get(toggle);
        }
        return false;
    }

    public boolean isPageEnabled(PageName pageName) {
        Optional<String> toggle = pageName.getToggle();
        return toggle.map(this::isEnabled).orElse(true);
    }
}

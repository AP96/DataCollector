package com.microsoft.datacollector.evaluator;

import software.amazon.awssdk.services.ec2.model.Instance;

import static com.microsoft.datacollector.constants.InstanceValues.COMPLIANT_TAG_KEY;
import static com.microsoft.datacollector.constants.InstanceValues.COMPLIANT_TAG_VALUE;

public class EC2InstanceComplianceChecker {

    public boolean isCompliant(Instance instance) {
        return instance.tags()
                .stream()
                .anyMatch(tag -> COMPLIANT_TAG_KEY.equals(tag.key()) && COMPLIANT_TAG_VALUE.equals(tag.value()));
    }
}

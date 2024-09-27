package com.korit.senicare.common.object;

import com.korit.senicare.entity.NurseEntity;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Nurse {
    private String nurseId;
    private String name;
    private String telNumber;

    private Nurse (NurseEntity nurseEntity) {
        this.nurseId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNumber = nurseEntity.getTelNumber();
    }

    public static List<Nurse> getList(List<NurseEntity> nurseEntities) {

        List<Nurse> nurses = new ArrayList<>();
        for (NurseEntity nurseEntity: nurseEntities) {
            Nurse nurse = new Nurse(nurseEntity);
            nurses.add(nurse);
        }
        return nurses;

    }
}

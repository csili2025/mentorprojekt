package edu.itplus.bibliosping.backend.model;

import java.util.Objects;
import java.util.UUID;

public class AbstractModel {
    private String uuid;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractModel that = (AbstractModel) o;
        return Objects.equals(getUuid(), that.getUuid());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

    public String getUuid() {
        if(uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

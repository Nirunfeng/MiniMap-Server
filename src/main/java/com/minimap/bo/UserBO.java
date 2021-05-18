package com.minimap.bo;

/**
 * 上传头像使用的封装对象
 */
public class UserBO {
    private String userId;
    private String faceData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }
}

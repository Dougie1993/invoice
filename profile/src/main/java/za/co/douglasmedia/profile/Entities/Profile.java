package za.co.douglasmedia.profile.Entities;

import jakarta.persistence.*;

@Entity // maps this class to our database
@Table
public class Profile {
    @Id
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_sequence"
            // how the id and sequence are generated
    )
    private long profileId;
    private String businessName, address, phone, mobile, userId, logoString;
    private boolean deactivated;

    public Profile() {
    }

    public Profile(String businessName, String address, String phone, String mobile, String userId,
                   String logoString, boolean deactivated) {
        this.businessName = businessName;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.userId = userId;
        this.logoString = logoString;
        this.deactivated = deactivated;
    }

    public Profile(long profileId, String businessName, String address, String phone, String mobile,
                   String userId, String logoString, boolean deactivated) {
        this.profileId = profileId;
        this.businessName = businessName;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.userId = userId;
        this.logoString = logoString;
        this.deactivated = deactivated;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLogoString() {
        return logoString;
    }

    public void setLogoString(String logoString) {
        this.logoString = logoString;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", businessName='" + businessName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userId='" + userId + '\'' +
                ", logoString='" + logoString + '\'' +
                ", deactivated=" + deactivated +
                '}';
    }
}

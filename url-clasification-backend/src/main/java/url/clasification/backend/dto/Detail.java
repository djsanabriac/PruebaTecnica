package url.clasification.backend.dto;

public class Detail {
    private String ipAddress;
    private String cidrBlock;
    private String announcingNetwork;
    private String rir;
    private String country;
    private String detailTime;

    public String getIPAddress() { return ipAddress; }
    public void setIPAddress(String value) { this.ipAddress = value; }

    public String getCIDRBlock() { return cidrBlock; }
    public void setCIDRBlock(String value) { this.cidrBlock = value; }

    public String getAnnouncingNetwork() { return announcingNetwork; }
    public void setAnnouncingNetwork(String value) { this.announcingNetwork = value; }

    public String getRIR() { return rir; }
    public void setRIR(String value) { this.rir = value; }

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public String getDetailTime() { return detailTime; }
    public void setDetailTime(String value) { this.detailTime = value; }
}
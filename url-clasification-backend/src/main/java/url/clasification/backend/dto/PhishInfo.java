package url.clasification.backend.dto;

import url.clasification.backend.dto.Detail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PhishInfo {
    private String phishID;
    private String url;
    private String phishDetailURL;
    private String submissionTime;
    private String verified;
    private String verificationTime;
    private String online;
    private Detail[] details;
    private String target;

    public String getPhishID() { return phishID; }
    public void setPhishID(String value) { this.phishID = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getPhishDetailURL() { return phishDetailURL; }
    public void setPhishDetailURL(String value) { this.phishDetailURL = value; }

    public String getSubmissionTime() { return submissionTime; }
    public void setSubmissionTime(String value) { this.submissionTime = value; }

    public String getVerified() { return verified; }
    public void setVerified(String value) { this.verified = value; }

    public String getVerificationTime() { return verificationTime; }
    public void setVerificationTime(String value) { this.verificationTime = value; }

    public String getOnline() { return online; }
    public void setOnline(String value) { this.online = value; }

    public Detail[] getDetails() { return details; }
    public void setDetails(Detail[] value) { this.details = value; }

    public String getTarget() { return target; }
    public void setTarget(String value) { this.target = value; }

    @Override
    public String toString() {
        return "PhishInfo{" +
                "phishID='" + phishID + '\'' +
                ", url='" + url + '\'' +
                ", phishDetailURL='" + phishDetailURL + '\'' +
                ", submissionTime='" + submissionTime + '\'' +
                ", verified='" + verified + '\'' +
                ", verificationTime='" + verificationTime + '\'' +
                ", online='" + online + '\'' +
                ", details=" + Arrays.toString(details) +
                ", target='" + target + '\'' +
                '}';
    }

    public static PhishInfo fromMap(Map<String, String> map){

        if (map == null){
            return null;
        }

        PhishInfo pi = new PhishInfo();

        pi.phishID = map.get("phish_id");
        pi.url = map.get("url");
        pi.phishDetailURL = map.get("phish_detail_url");
        pi.submissionTime = map.get("submission_time");
        pi.verified = map.get("verified");
        pi.verificationTime = map.get("verification_time");
        pi.online = map.get("online");
        pi.target = map.get("target");

        return pi;
    }

}

/*
* {
    "phish_id":"6397314",
    "url":"https:\/\/zonasegurasbeta.bnweb.me\/",
    "phish_detail_url":"http:\/\/www.phishtank.com\/phish_detail.php?phish_id=6397314",
    "submission_time":"2020-02-11T18:36:58+00:00",
    "verified":"yes",
    "verification_time":"2020-02-11T18:37:50+00:00",
    "online":"yes",
    "details":
    [
        {"ip_address":"198.187.29.115",
        "cidr_block":"198.187.28.0\/22",
        "announcing_network":"22612",
        "rir":"arin",
        "country":"US",
        "detail_time":"2020-02-11T18:37:31+00:00"}
    ],
    "target":"Other"
}
*
* */
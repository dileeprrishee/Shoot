package com.example.Shoot.Pojo.CurrentMatch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentMatchResult {

@SerializedName("match_id")
@Expose
private String matchId;
@SerializedName("details")
@Expose
private List<CurrentMatchData> details = null;

public String getMatchId() {
return matchId;
}

public void setMatchId(String matchId) {
this.matchId = matchId;
}

public List<CurrentMatchData> getDetails() {
return details;
}

public void setDetails(List<CurrentMatchData> details) {
this.details = details;
}

}
package dev.kemikals.guacamole.commands.trivia;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

@SerializedName("response_code")
@Expose
private int responseCode;
@SerializedName("results")
@Expose
private List<Result> results = null;

public int getResponseCode() {
return responseCode;
}

public void setResponseCode(int responseCode) {
this.responseCode = responseCode;
}

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

}
package myview.zz.com.zzcoursedata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * params{
 *  id: string 课程唯一id
 *  name: string 课程名
 *  describe: string 课程描述
 *  owned: boolean 是否拥有课程权限
 *  clicks: int 点击量
 *  buys: int 购买量
 *  rate: int 评分
 *  videos: string[] 下属课程id
 * }
 */

public class CourseData implements Parcelable {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("describe") public String describe;
    @SerializedName("owned") public Boolean owned;
    @SerializedName("clicks") public int clicks;
    @SerializedName("buys") public int buys;
    @SerializedName("rate") public int rate;
    @SerializedName("videos") public List<String> videos;
    private CourseData(Parcel in) {
    }
    public static final Creator<CourseData> CREATOR = new Creator<CourseData>() {
        @Override
        public CourseData createFromParcel(Parcel in) {
            return new CourseData(in);
        }
        @Override
        public CourseData[] newArray(int size) {
            return new CourseData[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}

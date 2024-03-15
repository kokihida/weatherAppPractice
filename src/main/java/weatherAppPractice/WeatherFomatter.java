package weatherAppPractice;

/**
 * jsonで返却された天気情報を日本の表現に変更するフォーマットクラス
 * @author 肥田
 */

public class WeatherFomatter {

	/**
	 * 英語表記の天気情報を日本語表記に変更しするためのメソッド
	 */
	public String weatherfomatter(String str) {
		
		String result = null;
		switch (str) {
		case "Clear":
			result = "晴れ";
			break;
		case "Clouds":
			result = "曇り";
			break;
		case "rain":
			result = "雨";
			break;
		}

		return result;

	}
}

package weatherAppPractice;

/**
 * 華氏温度を摂氏温度に変換し戻すメソッド
 * @author 肥田
 * @return 摂氏温度
 */
public class TemperatureChange {

	public double temperatureChange(double d) {
		return Math.round((d - 32) * 5 / 9 * 10) / 10.0;
	}
}

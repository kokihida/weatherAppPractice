package weatherAppPractice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 外部API openweatherAPIを使用し天気情報を取得するシステム
 * @author 肥田
 */
public class WeatherAppSuccess {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		//japanの部分をアメリカ、日本、他47カ国入力されたものに応じて返すようにしたい
		//天気についてもClearなら晴れといった具合に出力を変えたい
		//他の天気情報も提供されているがそいつらは実装しないでOKする
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://open-weather13.p.rapidapi.com/city/japan"))
				.header("X-RapidAPI-Key", "1ece860defmsh288001227c1d717p1276cdjsn083cd1a2b964")
				.header("X-RapidAPI-Host", "open-weather13.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			//取得したjsonは一旦このまま後で消してOK
//			System.out.println(response.body());

			/**
			 * 以下APIからレスポンスされたjsonの中から出力するための処理
			 */
			String jsonResponse = response.body();

			JSONObject obj = new JSONObject(jsonResponse);

			// 天気情報を取得
			WeatherFomatter weatherFormatter = new WeatherFomatter();
			
			//日本語表記の天候を格納する
			String weatherJapanese;
			JSONArray weatherArray = obj.getJSONArray("weather");
			JSONObject weather = weatherArray.getJSONObject(0);
			weatherJapanese = weatherFormatter.weatherfomatter(weather.getString("main"));
			
			
			String description = weather.getString("description");

			// 温度情報を取得
			TemperatureChange tempertureCange = new TemperatureChange();
			double temp;
			JSONObject mainObj = obj.getJSONObject("main");
			temp = tempertureCange.temperatureChange(mainObj.getDouble("temp"));

			// 結果を表示
			System.out.println("天気: " + weatherJapanese + " (" + description + ")");
			System.out.println("気温: " + temp + "℃");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}

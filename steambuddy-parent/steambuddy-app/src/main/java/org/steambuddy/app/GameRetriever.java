package org.steambuddy.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GameRetriever {

	// rating 74

	public static void main(String[] args) throws IOException {
		File sql = new File("data.sql");
		FileWriter fwFileWriter = new FileWriter(sql);

		fwFileWriter.write("insert ignore into game (id, name, year, text, image) values ");

		getGameInfos().forEach(game -> {
			try {
				fwFileWriter.write(game);

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		fwFileWriter.write(";");
		fwFileWriter.close();
	}

	private static List<String> getGameInfos() throws IOException {

		List<String> games = new ArrayList<>();

		OkHttpClient client = new OkHttpClient();

		RequestBody rb = RequestBody.create(MediaType.parse("text/plain"),
				"fields name, cover, release_dates.human, summary ; where platforms = 6 & rating > 74; limit 500;");

		Request gameRequest = new Request.Builder().url("https://api.igdb.com/v4/games/")
				.addHeader("Client-ID", "aagdqwj9ymhot013cfc6kro5xsn920")
				.addHeader("Authorization", "Bearer sdit264ekyyqeuv8kr0ogbe6nfygmt").post(rb).build();

		Response response = client.newCall(gameRequest).execute();
		JSONArray result = new JSONArray(response.body().string());

		for (int i = 0; i < result.length(); i++) {
			JSONObject jo = result.getJSONObject(i);

			Integer id = jo.getInt("id");
			String name = "'" + jo.getString("name").replaceAll("'", "`") + "'";

			String year = jo.getJSONArray("release_dates").getJSONObject(0).getString("human");
			
			if (year.length() < 4) {
				year = "null";
			} else {
				year = year.substring(year.length() - 4);
			}
			String summary = jo.keySet().contains("summary") ? "'" + jo.getString("summary").replaceAll("'", "`") + "'"
					: "null";

			Integer cover = jo.keySet().contains("cover") ? jo.getInt("cover") : null;
			String coverUrl = "null";

			if (cover != null) {
				coverUrl = getCover(cover);
			}

			String sqlValues = "(" + id + ", " + name + ", " + year + ", " + summary + ", " + coverUrl + ")";
			if (i < result.length() - 1)
				sqlValues += ",\n";
			games.add(sqlValues);
			System.out.println("Game " + i + " of " + result.length() + " processed");
		}

		System.out.println("found " + result.length() + " games");
		return games;
	}

	private static String getCover(int id) throws IOException {

		OkHttpClient client = new OkHttpClient();

		RequestBody rb = RequestBody.create(MediaType.parse("text/plain"), "fields * ; where id = " + id + ";");

		Request gameRequest = new Request.Builder().url("https://api.igdb.com/v4/covers")
				.addHeader("Client-ID", "aagdqwj9ymhot013cfc6kro5xsn920")
				.addHeader("Authorization", "Bearer sdit264ekyyqeuv8kr0ogbe6nfygmt").post(rb).build();

		Response response = client.newCall(gameRequest).execute();
		JSONArray result = new JSONArray(response.body().string());
		if (result.length() == 0)
			return "null";
		String url = "https://images.igdb.com/igdb/image/upload/t_cover_big/";

		String imageId = result.getJSONObject(0).getString("image_id");
		return "'" + url + imageId + ".jpg'";

	}

}

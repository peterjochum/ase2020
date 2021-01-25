package org.steambuddy.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GameRetriever {

	// rating 74

	/*private static Map<Integer, String> publisher = new HashMap<>();
	private static Map<Integer, String> genres = new HashMap<>();
	private static Map<Integer, Integer> genreGames = new HashMap<>();

	public static void main(String[] args) throws IOException {
		File sql = new File("data.sql");
		FileWriter fw = new FileWriter(sql);
		int maxId = 500;
		List<String> games = new ArrayList<>();

		for (int i = 1; i < 1000; i++) {
			games.addAll(getGameInfos(maxId * i));
		}
		writeToData("insert ignore into publisher (id, name) values ", publisher, fw);
		writeToData("insert ignore into genre (id, name) values ", genres, fw);

		fw.write("insert ignore into game (id, name, year, text, image, publisher_id) values ");

		games.forEach(game -> {
			try {
				fw.write(game + "\n");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		fw.write(";\n");

		writeToData("insert ignore into game_genres (genres_id, game_id) values ", genreGames, fw);

		fw.close();
	}

	private static <X> void writeToData(String insert, Map<Integer, X> values, FileWriter fw) throws IOException {
		Iterator<Integer> iter = values.keySet().iterator();
		fw.write(insert);
		while (iter.hasNext()) {
			Integer id = iter.next();
			String out = "(" + id + ", " + values.get(id) + ")";
			if (iter.hasNext()) {
				out += ",\n";
			}
			fw.write(out);
		}
		fw.write(";\n");

	}

	private static List<String> getGameInfos(int maxId) throws IOException {

		List<String> games = new ArrayList<>();

		OkHttpClient client = new OkHttpClient();

		RequestBody rb = RequestBody.create(MediaType.parse("text/plain"),
				"fields name, cover, release_dates.human, summary, genres, involved_companies ; where platforms = 6 & rating > 60 & id < "
						+ maxId + " & id >= " + (maxId - 500) + "; limit 500 ;");
//				"fields * ; where platforms = 6 & rating > 74; limit 500;");

		// genres - array
		// involved_companies

		Request gameRequest = new Request.Builder().url("https://api.igdb.com/v4/games/")
				.addHeader("Client-ID", "aagdqwj9ymhot013cfc6kro5xsn920")
				.addHeader("Authorization", "Bearer sdit264ekyyqeuv8kr0ogbe6nfygmt").post(rb).build();

		Response response = client.newCall(gameRequest).execute();
		JSONArray result = new JSONArray(response.body().string());

		for (int i = 0; i < result.length(); i++) {

			JSONObject jo = result.getJSONObject(i);
			// System.out.println(jo.toString());
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

			String pid = "null";
			if (jo.keySet().contains("involved_companies"))
				pid = addPublisher(id);

			if (jo.keySet().contains("genres"))
				addGenre(jo.getJSONArray("genres"), id);

			String sqlValues = "(" + id + ", " + name + ", " + year + ", " + summary + ", " + coverUrl + ", " + pid
					+ ")";
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

	private static String addPublisher(int id) throws IOException {

		OkHttpClient client = new OkHttpClient();

		RequestBody rb = RequestBody.create(MediaType.parse("text/plain"), "fields * ; where published = " + id + ";");

		Request gameRequest = new Request.Builder().url("https://api.igdb.com/v4/companies")
				.addHeader("Client-ID", "aagdqwj9ymhot013cfc6kro5xsn920")
				.addHeader("Authorization", "Bearer sdit264ekyyqeuv8kr0ogbe6nfygmt").post(rb).build();

		Response response = client.newCall(gameRequest).execute();
		JSONArray result = new JSONArray(response.body().string());
		// System.out.println(result.toString());

		if (result.length() == 0)
			return "null";

		String name = result.getJSONObject(0).getString("name").replaceAll("'", "`");
		Integer pid = result.getJSONObject(0).getInt("id");
		publisher.put(pid, "'" + name + "'");
		System.out.println("Found publisher " + name);
		return pid + "";
	}

	private static void addGenre(JSONArray ids, Integer gameId) throws IOException {

		OkHttpClient client = new OkHttpClient();

		String idsForQuery = "( ";
		for (int i = 0; i < ids.length(); i++) {
			idsForQuery += ids.getInt(i) + ",";
		}
		idsForQuery = idsForQuery.substring(0, idsForQuery.length() - 1);
		idsForQuery += ")";

		RequestBody rb = RequestBody.create(MediaType.parse("text/plain"),
				"fields * ; where id = " + idsForQuery + ";");

		Request gameRequest = new Request.Builder().url("https://api.igdb.com/v4/genres")
				.addHeader("Client-ID", "aagdqwj9ymhot013cfc6kro5xsn920")
				.addHeader("Authorization", "Bearer sdit264ekyyqeuv8kr0ogbe6nfygmt").post(rb).build();

		Response response = client.newCall(gameRequest).execute();
		JSONArray result = new JSONArray(response.body().string());
		// System.out.println(result.toString());

		if (result.length() == 0)
			return;

		for (int i = 0; i < result.length(); i++) {
			String name = "'" + result.getJSONObject(0).getString("name").replaceAll("'", "`") + "'";
			Integer pid = result.getJSONObject(0).getInt("id");
			genres.put(pid, name);
			genreGames.put(pid, gameId);
		}
	}*/

}

package dk.venummc.venumcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class Licens {
	private String licenseKey;
	private Plugin plugin;
	private String validationServer;
	private LogType logType = LogType.NORMAL;
	private String securityKey = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";
	private boolean debug = false;

	public Licens(String licenseKey, String validationServer, Plugin plugin) {
		this.licenseKey = licenseKey;
		this.plugin = plugin;
		this.validationServer = validationServer;
	}
	public Licens setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
		return this;
	}
	public Licens setConsoleLog(LogType logType) {
		this.logType = logType;
		return this;
	}
	public Licens debug() {
		debug = true;
		return this;
	}
	public boolean register() {
		log(0, "");
		log(0, "Tjekker din licens...");
		ValidationType vt = isValid();
		if (vt == ValidationType.VALID) {
			log(1, "Din licens er gyldig!");
			log(0, "");
			return true;
		} else {
			log(1, "Licensen er IKKE gyldig!");
			log(1, "Mislykkedes som følge af " + vt.toString());
			log(0, "");

			Bukkit.getScheduler().cancelTasks(plugin);
			Bukkit.getPluginManager().disablePlugin(plugin);
			return false;
		}
	}
	public boolean isValidSimple() {
		return (isValid() == ValidationType.VALID);
	}
	private String requestServer(String v1, String v2) throws IOException {
		URL url = new URL(validationServer + "?v1=" + v1 + "&v2=" + v2 + "&pl=" + plugin.getName());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		if (debug) {
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
		}
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in .readLine()) != null) {
				response.append(inputLine);
			}

			return response.toString();
		}
	}
	public ValidationType isValid() {
		String rand = toBinary(UUID.randomUUID().toString());
		String sKey = toBinary(securityKey);
		String key = toBinary(licenseKey);
		try {
			String response = requestServer(xor(rand, sKey), xor(rand, key));
			if (response.startsWith("<")) {
				log(1, "Licens-serveren returnerede et ugyldigt svar!");
				log(1, "I de fleste tilfælde er dette forårsaget af:");
				log(1, "1) Din webvært injicerer JS på siden (ofte forårsaget af gratis værter)");
				log(1, "2) Din ValidationServer-URL er forkert");
				log(1,
						"SERVER-SVAR: " + (response.length() < 150 || debug ? response : response.substring(0, 150) + "..."));
				return ValidationType.PAGE_ERROR;
			}
			try {
				return ValidationType.valueOf(response);
			} catch (IllegalArgumentException exc) {
				String respRand = xor(xor(response, key), sKey);
				if (rand.substring(0, respRand.length()).equals(respRand))
					return ValidationType.VALID;
				else
					return ValidationType.WRONG_RESPONSE;
			}
		} catch (IOException e) {
			if (debug)
				e.printStackTrace();
			return ValidationType.PAGE_ERROR;
		}
	}
	private static String xor(String s1, String s2) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
			result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
		return result.toString();
	}
	public enum LogType {
		NORMAL,
		LOW,
		NONE;
	}
	public enum ValidationType {
		WRONG_RESPONSE,
		PAGE_ERROR,
		URL_ERROR,
		KEY_OUTDATED,
		KEY_NOT_FOUND,
		NOT_VALID_IP,
		INVALID_PLUGIN,
		VALID;
	}
	private String toBinary(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b: bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}
	private void log(int type, String message) {
		if (logType == LogType.NONE || (logType == LogType.LOW && type == 0))
			return;
		System.out.println(message);
	}
}
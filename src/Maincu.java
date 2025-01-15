import java.awt.Rectangle;

import org.lwjgl.glfw.GLFW;
import org.martinlibrary.core.Color;
import org.martinlibrary.core.Input;
import org.martinlibrary.core.Renderer;
import org.martinlibrary.core.Texture;
import org.martinlibrary.core.Window;

public class Maincu {

	public static void main(String[] args) throws InterruptedException {
		Window.create(800, 600, "TenisPongGotyEdition");
		Texture tenistaazul = new Texture("src/tenistaazul.png");
		Texture tenistaverde = new Texture("src/tenistaverde.png");
		Texture bolatenis = new Texture("src/bolatenis.png");
		float x = 0;
		float y = (600 - 48) / 2;
		Texture grassTexture = new Texture("src/grass.png");
		float ballSize = 16;
		float ballY = (600 - ballSize) / 2;
		float ballX = (800 - ballSize) / 2;
		float ballVelX = -1.3f;
		float ballVelY = 0.7f;
		Rectangle ballrect = new Rectangle();
		Rectangle playerrect = new Rectangle();
		boolean isPlaying = false;

		float player1x = 800 - 48;
		float player1y = (600 - 48) / 2;
		Rectangle player1rect = new Rectangle();

		while (Window.shouldClose() == false) {
			Renderer.clear(Color.BLACK);
			for (int i = 0; i < 800 / 48 + 1; i++) {
				for (int j = 0; j < 600 / 48 + 1; j++) {
					Renderer.drawTexture(grassTexture, i * 48, j * 48, 48, 48);

				}

			}

			if (Input.isKeyDown(Input.KEY_W)) {
				y += 0.7f;
				isPlaying = true;
			}
			if (Input.isKeyDown(Input.KEY_D)) {
				x += 0.7f;
				isPlaying = true;
			}

			if (Input.isKeyDown(Input.KEY_A)) {
				x -= 0.7f;
				isPlaying = true;
			}
			if (Input.isKeyDown(Input.KEY_S)) {
				y -= 0.7f;
				isPlaying = true;

			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_UP)) {
				player1y += 0.7f;
				isPlaying = true;
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
				player1y -= 0.7f;
				isPlaying = true;
			}

			if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
				player1x -= 0.7f;
				isPlaying = true;
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
				player1x += 0.7f;
				isPlaying = true;

			}
			if (isPlaying == true) {

				ballX += ballVelX;
				ballY += ballVelY;
			}

			if (x < 0) {
				x = 0;

			}

			if (x > (800 - 5) / 2 - 48) {
				x = (800 - 5) / 2 - 48;
			}
			if (y > 500 - 48) {
				y = 500 - 48;
			}
			if (y < 100) {
				y = 100;

			}
			if (player1x > 800 - 48) {
				player1x = 800 - 48;

			}

			if (player1x < (800 - 5) / 2) {
				player1x = (800 - 5) / 2;
			}
			if (player1y > 500 - 48) {
				player1y = 500 - 48;
			}
			if (player1y < 100) {
				player1y = 100;
			}

			Renderer.drawQuad((800 - 600) / 2, 100, 600, 5);
			Renderer.drawQuad((800 - 600) / 2, 500, 600, 5);
			Renderer.drawQuad((800 - 600) / 2, 100, 5, 400);
			Renderer.drawQuad((800 - 600) / 2 + 600 - 5, 100, 5, 400);
			Renderer.setColor(Color.BLACK);
			Renderer.drawQuad((800 - 5) / 2, 105, 5, 400 - 5);
			Renderer.setColor(Color.WHITE);

			Renderer.drawTexture(tenistaazul, x, y, 48, 48);
			Renderer.drawTexture(tenistaverde, player1x, player1y, 48, 48);
			Renderer.drawTexture(bolatenis, ballX, ballY, ballSize, ballSize);

			ballrect.setBounds((int) ballX, (int) ballY, (int) ballSize, (int) ballSize);
			playerrect.setBounds((int) x, (int) y, 48, 48);
			player1rect.setBounds((int) player1x, (int) player1y, 48, 48);

			if (playerrect.intersects(ballrect)) {
				ballVelX = 1.3f;
			}

			if (player1rect.intersects(ballrect)) {
				ballVelX = -1.3f;

			}

			if (ballY > 500 - ballSize) {
				ballVelY = -ballVelY;
			}
			if (ballY < 100) {
				ballVelY = -ballVelY;
			}
			if (ballX < -ballSize || ballX > 800) {
				isPlaying = false;
				ballY = (600 - ballSize) / 2;
				ballX = (800 - ballSize) / 2;
				player1x = 800 - 48;
				player1y = (600 - 48) / 2;
				x = 0;
				y = (600 - 48) / 2;
			}

			Thread.sleep(2);
			Window.update();
		}

	}

}

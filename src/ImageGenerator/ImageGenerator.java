package ImageGenerator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerator {
	static int pixel_rate = 2;
	static String write_txt = "/Users/gihyeokkwon/Documents/test.txt";
	static String read_image = "/Users/gihyeokkwon/Documents/123.jpeg"; // 이미지 로고 주소

	public static void main(String[] args) throws IOException {
		File imgFile = null;
		File mfile = new File(write_txt);
		BufferedWriter writer = null;
		int threshold[] = { 40, 60, 100, 140, 180 };

		int num;
		try {
			imgFile = new File(read_image);
		} catch (Exception e) {
			// TODO Auto-generated catch block            
			e.printStackTrace();
		}
		writer = new BufferedWriter(new FileWriter("file.txt", true));
		BufferedImage image = getImage(imgFile);
		for (int i = 0; i < image.getHeight(); i += pixel_rate) {
			for (int j = 0; j < image.getWidth(); j += pixel_rate) {
				Color color = new Color(image.getRGB(j, i));
				num = Math.min(Math.min(color.getRed(), color.getBlue()), color.getGreen());
				if (num < threshold[0] || num < threshold[1] || num < threshold[2] || num < threshold[3]) {
					writer.write(String.format("%s", "■"));
					System.out.print(String.format("%s", "■"));
				} else {
					writer.write(String.format("%s", "☐"));
					System.out.print(String.format("%s", "☐"));
				}
			}
			writer.newLine();
			System.out.println();
		}
		writer.close();
	}

	private static BufferedImage getImage(File file) throws IOException {
		BufferedImage originalImage = ImageIO.read(file);
		return originalImage;
	}
}

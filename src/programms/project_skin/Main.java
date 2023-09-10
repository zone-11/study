package programms.project_skin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\projects\\test_package\\skin_copy.png");
		File file2 = new File("C:\\projects\\test_package\\Mag.png");
		MinecraftSkinGenerator.resetCounts();
		try {
			MinecraftSkinGenerator.parseSkin(ImageIO.read(file2));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnhandledSkinSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ImageIO.write(MinecraftSkinGenerator.generateSkin().getSkinImage(), "png", file);
	}
}




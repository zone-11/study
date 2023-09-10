package programms.project_skin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

class MinecraftSkinGenerator {
	private static File HEADS = new File("C:\\Users\\SkillFullMuster\\eclipse-workspace\\study\\src\\programms\\project_skin\\head");
	private static File BODIES = new File("C:\\Users\\SkillFullMuster\\eclipse-workspace\\study\\src\\programms\\project_skin\\body");
	private static File HANDS = new File("C:\\Users\\SkillFullMuster\\eclipse-workspace\\study\\src\\programms\\project_skin\\hands");
	private static File LEGS = new File("C:\\Users\\SkillFullMuster\\eclipse-workspace\\study\\src\\programms\\project_skin\\legs");
	
	private static Preferences prefs = Preferences.userNodeForPackage(MinecraftSkinGenerator.class);
	
	private static Random random = new Random();
	
	public static MinecraftSkin generateSkin() throws IOException {
		MinecraftSkin newSkin;
		try {
			newSkin = MinecraftSkin.createSkin(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
			BufferedImage[] skinParts = 
			{
				ImageIO.read(HEADS.listFiles()[random.nextInt(0, prefs.getInt("head_count", 1))]),
				ImageIO.read(BODIES.listFiles()[random.nextInt(0, prefs.getInt("body_count", 1))]),
				ImageIO.read(HANDS.listFiles()[random.nextInt(0, prefs.getInt("hands_count", 1))]),
				ImageIO.read(HANDS.listFiles()[random.nextInt(0, prefs.getInt("hands_count", 1))]),
				ImageIO.read(LEGS.listFiles()[random.nextInt(0, prefs.getInt("legs_count", 1))]),
				ImageIO.read(LEGS.listFiles()[random.nextInt(0, prefs.getInt("legs_count", 1))])
			};
			for(int i = 0; i < skinParts.length; i++) {
				newSkin.setSkinPart(skinParts[i], SkinPart.values()[i]);
			}
			return newSkin;
		} catch (UnhandledSkinSizeException e) {
			System.err.println("Error via create skin");
		}
		return null;
	}
	
	public static void parseSkin(BufferedImage image) throws IOException,
	UnhandledSkinSizeException {
		MinecraftSkin skin = MinecraftSkin.createSkin(image);
		for(MinecraftSkinPart skinPart : skin.getSkinParts()) {
			savePart(skinPart);
		}
	}
	public static void resetCounts() {
		prefs.putInt("head_count", 0);
		prefs.putInt("body_count", 0);
		prefs.putInt("hands_count", 0);
		prefs.putInt("legs_count", 0);
	}
	private static void savePart(MinecraftSkinPart part) throws IOException {
		BufferedImage img = part.getImage();
		switch(part.getSkinPartType()) {
		case HEAD:
			save("head_count", img, HEADS);
			break;
		case BODY:
			save("body_count", img, BODIES);
			break;
		case HAND1: 
			save("hands_count", img, HANDS);
			break;
		case HAND2: 
			save("hands_count", img, HANDS);
			break;
		case LEG1:
			save("legs_count", img, LEGS);
			break;
		case LEG2:
			save("legs_count", img, LEGS);
			break;
		}
	}
	
	private static void save(String key, BufferedImage bImage, File dir) throws IOException{
		String path = dir.getAbsolutePath() + "\\" + prefs.getInt(key, 0) + ".png";
		System.out.println(dir.getAbsolutePath());
		prefs.putInt(key, prefs.getInt(key, 0) + 1);
		File file = new File(path);
		file.createNewFile();
		ImageIO.write(bImage, "png", file);
		System.out.println("Saving...");
	}
}

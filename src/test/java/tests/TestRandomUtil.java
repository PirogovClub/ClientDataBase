package tests;



import utils.RandomData;

public class TestRandomUtil {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomData RandomGenerator = new RandomData();
		RandomData.LanguageSets LanguageSets = null;
		System.out.println(RandomGenerator.getRandomString(10, LanguageSets.ENGLISH_HIGH));
		System.out.println(RandomGenerator.getRandomInt(-1000, 10));
		System.out.println(RandomGenerator.getRandomBoolean());
		System.out.println(RandomGenerator.getRandomFloat(2, 10, 2));
	}

}

package hw1;

public class hw1 {
	
	public static void main(String[] args) {
		int L = 0x01234567; // using type int because its 32 bit, and when shifting left the bits fall off
		int R = 0x89abcdef; // splitting the plain text into two blocks
		
		int K1 = 0xa56babcd; // splitting the key into four blocks
		int K2 = 0xf000ffff;
		int K3 = 0xffffffff;
		int K4 = 0xAbcdef01;
		
		int delta = 0x9e3779b9;
		int sum = 0;
		for(int i = 0; i < 32;) {
			sum+= delta;
			L += ((R<<4)+ K1) ^ (R+sum) ^ ((R>>>5)+K2); //implementing the TEA algorithm with >>> because it shifts the bits with an unsigned parameter.
			R += ((L<<4)+K3) ^ (L+sum) ^ ((L>>>5)+K4);
			i++;
		}
		System.out.println("Left side of the Cipher:");
		System.out.println(String.format("0x%08X", L)); // Printing out the ciphertext.
		System.out.println("Right side of the Cipher:");
		System.out.println(String.format("0x%08X", R));
		int sum1 = delta<<5; // RESETTING SUM
		
		for(int i = 0; i < 32;) {
			R -= ((L<<4)+ K3) ^ (L+sum1) ^ ((L>>>5)+K4); //REVERSING THE ORDER
			L -= ((R<<4)+K1) ^ (R+sum1) ^ ((R>>>5)+K2);
			sum1 -= delta;
			i++;
		}
		System.out.println("SUCCESS DECRYPTING");
		System.out.println(String.format("0x%08X", L));
		System.out.println(String.format("0x%08X", R));




		

		
		

	}

}

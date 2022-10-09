package demostrations.messageEncryption;

import algoritms.rabbit.Rabbit;
import utils.Utils;

public class MessageEncryptionWithRabbit {

	public static void main(String[] args) {
		encryptionMessageDemonstration();
	}

	public static void encryptionMessageDemonstration() {
		System.out.println("Iniciando demostración de encriptación de mensaje..");
		Rabbit rabbit = new Rabbit();
		String key = "secret key 12345";	//16 bytes
		String iv = "iv 12345";						//8 bytes
		String message = "La temperatura actual de la cámara frigorífica es de -2°C";

		System.out.printf("\n mensaje a encriptar: %s\n", message);

		byte[] crypt = rabbit.execute(message.getBytes(), key.getBytes(), iv.getBytes());
		System.out.printf("\n mensaje encriptado: %s\n", Utils.byteToString(crypt));

		crypt = rabbit.execute(crypt, key.getBytes(), iv.getBytes());
		System.out.printf("\n mensaje desencriptado: %s\n", Utils.byteToString(crypt));

		// CHEQUEO QUE COINCIDAN
		System.out.println();
		System.out.print("Resultado del test: ");
		if (message.equals(Utils.byteToString(crypt))) {
			System.out.println("SUCCESS");
		} else {
			System.out.println("FAILED");
		}
	}
}
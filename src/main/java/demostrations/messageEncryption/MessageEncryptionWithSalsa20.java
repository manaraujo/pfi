package demostrations.messageEncryption;

import algoritms.salsa20.Salsa20;
import utils.Utils;

public class MessageEncryptionWithSalsa20 {

    public static void main(String[] args) {
        encryptionMessageDemonstration();
    }

    public static void encryptionMessageDemonstration() {
        System.out.println("Iniciando demostración de encriptación de mensaje..");
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        String message = "La temperatura actual de la cámara frigorífica es de -2°C";

        int[] intMessage = Utils.stringToInt(message);
        System.out.printf("\n mensaje a encriptar: %s\n", message);

        int[] crypt = salsa20.execute(k, n, intMessage, 0);
        System.out.printf("\n mensaje encriptado: %s\n", Utils.intToString(crypt));

        crypt = salsa20.execute(k, n, crypt, 0);
        System.out.printf("\n mensaje desencriptado: %s\n", Utils.intToString(crypt));
    }

}

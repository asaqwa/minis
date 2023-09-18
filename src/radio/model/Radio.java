package radio.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Radio {
    private boolean eingeschaltet;
    private double frequenz;
    private int lautstaerke;

    public static void main(String[] args) {
        Radio radio = new Radio();
        try (BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            while (!exit) {
                System.out.println(radio);
                System.out.println("Was möchten Sie tun?");
                System.out.println("1. " + (radio.eingeschaltet? "ausschalten.": "einschalten"));
                if (radio.eingeschaltet) {
                    System.out.println("2. lauter.");
                    System.out.println("3. leiser.");
                    System.out.println("4. Sender wechseln.");
                }
                System.out.println("Oder eine andere Taste für Exit.");
                switch (scanner.readLine()){
                    case "1":
                        if (radio.eingeschaltet) radio.aus();
                        else radio.an();
                        break;
                    case "2":
                        if (radio.eingeschaltet) radio.lauter();
                        else exit = true;
                        break;
                    case "3":
                        if (radio.eingeschaltet) radio.leiser();
                        else  exit = true;
                        break;
                    case "4":
                        System.out.println("Geben Sie bitte die Frequenz (z.B. 98.98) des Senders ein:");
                        try {
                            if (radio.eingeschaltet) radio.setSender(Double.parseDouble(scanner.readLine()));
                            else  exit = true;
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("\u001B[31m" + "Error." + "\u001B[30m");
                        }
                        break;
                    default:
                        exit = true;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Radio() {
        eingeschaltet = false;
        frequenz = 100;
        lautstaerke = 5;
    }

    public Radio(boolean eingeschaltet, double frequenz, int lautstaerke) {
        this.eingeschaltet = eingeschaltet;
        setFrequenz(frequenz);
        this.lautstaerke = lautstaerke;
    }

    public void an() {
        eingeschaltet = true;
    }

    public void aus() {
        eingeschaltet = false;
    }

    public int lauter() {
        if (eingeschaltet) lautstaerke = Math.min(10, lautstaerke+1);
        return lautstaerke;
    }

    public int leiser() {
        if (eingeschaltet) lautstaerke = Math.max(0, lautstaerke-1);
        return lautstaerke;
    }

    private void setFrequenz(double frequenz) {
        this.frequenz = (Math.round(frequenz*100))/100d;
    }

    public void setSender(double frequenz) {
        if (eingeschaltet) setFrequenz(frequenz);
    }

    @Override
    public String toString() {
        String radioZustand = eingeschaltet?
                String.format("eingeschaltet, Frequenz = %8.2f, Lautstärke = %s.", frequenz, lautstaerke) :
                "ausgeschaltet.";
        return "Das Radio ist " + radioZustand;
    }

    public boolean isEingeschaltet() {
        return eingeschaltet;
    }

    public double getFrequenz() {
        return frequenz;
    }

    public int getLautstaerke() {
        return lautstaerke;
    }
}

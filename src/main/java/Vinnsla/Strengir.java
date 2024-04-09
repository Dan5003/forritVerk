package Vinnsla;

public class Strengir {

    private String texti;

    public Strengir(){}



    public void setTexti(String texti) {
        this.texti = texti;
    }

    /**
     * tökum strenginn og splittum honum eftir bili.
     * athugum síðan hvort að leitarordid okkar sé eitt af orðunum.
     * */

    public int leita (String leitarord) throws NullPointerException {
        if (texti == null) {
            throw new NullPointerException();
        }
        else {
            String[] words = texti.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(leitarord)) {
                    return i + 1;
                }
            }
            return -1;
        }
    }

    /**
     * tökum strenginn og splittum honum eftir bili.
     * teljum síðan orðin.
     * */

    public int fjoldiOrda() throws NullPointerException {
        if (texti == null) {
            throw new NullPointerException();
        }
        else {
            String[] words = texti.split(" ");
            return words.length;
        }
    }


    public static void main(String[] args) {
    }
}
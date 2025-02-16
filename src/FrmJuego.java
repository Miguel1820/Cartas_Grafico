import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    public FrmJuego() {
        setSize(700,200);
        setTitle("Juguemos al apuntado!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10,10,100,25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120,10,100,25);
        getContentPane().add(btnVerificar);
    }

}

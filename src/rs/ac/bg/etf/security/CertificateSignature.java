package rs.ac.bg.etf.security;

import java.security.cert.X509Certificate;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CertificateSignature extends JDialog {

	private X509Certificate cert;
	
	public CertificateSignature(X509Certificate c) {
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Generated Certificate");
		setBounds(100, 100, 603, 520);
		setModal(true);
		getContentPane().setLayout(null);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cert = c;
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClose.setBounds(250, 444, 89, 23);
		getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 567, 420);
		getContentPane().add(scrollPane);
		
		JTextArea txtrCert = new JTextArea();
		scrollPane.setViewportView(txtrCert);
		txtrCert.setText(cert.toString());
		
	}
}

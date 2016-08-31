package rs.ac.bg.etf.security;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CertificatePreview extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private KeyPairInfo kpi;
	private JTextField txtV;
	private JTextField textField_11;

	public CertificatePreview(KeyPairInfo k) {
		setModal(true);
		kpi = k;
		setTitle("Certificate Preview");
		setBounds(100, 100, 535, 689);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("Key size:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(25, 53, 69, 21);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setEnabled(false);
		textField
				.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(82, 53, 164, 21);
		getContentPane().add(textField);

		JLabel label_2 = new JLabel("Certificate version:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(267, 21, 124, 21);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Validity period:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(273, 53, 98, 21);
		getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1
				.setToolTipText("");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(364, 53, 135, 21);
		getContentPane().add(textField_1);

		JLabel label_4 = new JLabel("Serial number:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(25, 85, 98, 21);
		getContentPane().add(label_4);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2
				.setToolTipText("");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(129, 87, 370, 21);
		getContentPane().add(textField_2);
		textField_2.setText(kpi.getSerialNumber().toString());
		JLabel label_5 = new JLabel("Alias:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(25, 21, 51, 21);
		getContentPane().add(label_5);

		textField_3 = new JTextField();
		textField_3.setForeground(Color.BLACK);
		textField_3.setEnabled(false);
		textField_3
				.setToolTipText("");
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(65, 21, 181, 21);
		getContentPane().add(textField_3);

		JLabel label_10 = new JLabel("Common Name:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_10.setBounds(25, 149, 111, 21);
		getContentPane().add(label_10);

		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(138, 149, 108, 21);
		getContentPane().add(textField_4);

		JLabel label_11 = new JLabel("Organizational Unit:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_11.setBounds(259, 150, 133, 19);
		getContentPane().add(label_11);

		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(401, 149, 98, 21);
		getContentPane().add(textField_5);

		JLabel label_12 = new JLabel("Organization:");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_12.setBounds(25, 181, 111, 21);
		getContentPane().add(label_12);

		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(138, 181, 108, 21);
		getContentPane().add(textField_6);

		JLabel label_13 = new JLabel("Localization:");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_13.setBounds(259, 182, 133, 19);
		getContentPane().add(label_13);

		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(401, 181, 98, 21);
		getContentPane().add(textField_7);

		JLabel label_14 = new JLabel("Country Name:");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_14.setBounds(25, 213, 111, 21);
		getContentPane().add(label_14);

		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setText("RS");
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(138, 213, 108, 21);
		getContentPane().add(textField_8);

		JLabel label_15 = new JLabel("State/Province Name:");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_15.setBounds(259, 214, 145, 19);
		getContentPane().add(label_15);

		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(401, 213, 98, 21);
		getContentPane().add(textField_9);

		JLabel label_16 = new JLabel("E-mail:");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_16.setBounds(25, 245, 111, 21);
		getContentPane().add(label_16);

		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_10.setColumns(10);
		textField_10.setBounds(138, 245, 163, 21);
		getContentPane().add(textField_10);

		JLabel label_21 = new JLabel("otherName");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_21.setBounds(25, 459, 75, 14);
		getContentPane().add(label_21);

		textField_12 = new JTextField();
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_12.setEnabled(false);
		textField_12.setColumns(10);
		textField_12.setBounds(137, 456, 109, 20);
		getContentPane().add(textField_12);

		JLabel label_22 = new JLabel("rfc822Name");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_22.setBounds(25, 487, 75, 14);
		getContentPane().add(label_22);

		textField_13 = new JTextField();
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_13.setEnabled(false);
		textField_13.setColumns(10);
		textField_13.setBounds(137, 484, 109, 20);
		getContentPane().add(textField_13);

		JLabel label_23 = new JLabel("DNS name");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_23.setBounds(288, 456, 79, 14);
		getContentPane().add(label_23);

		textField_14 = new JTextField();
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_14.setEnabled(false);
		textField_14.setColumns(10);
		textField_14.setBounds(384, 456, 115, 20);
		getContentPane().add(textField_14);

		JLabel label_24 = new JLabel("x400Address");
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_24.setBounds(288, 485, 97, 19);
		getContentPane().add(label_24);

		textField_15 = new JTextField();
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_15.setEnabled(false);
		textField_15.setColumns(10);
		textField_15.setBounds(384, 484, 115, 20);
		getContentPane().add(textField_15);

		JLabel label_25 = new JLabel("directoryName");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_25.setBounds(25, 512, 105, 17);
		getContentPane().add(label_25);

		textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_16.setEnabled(false);
		textField_16.setColumns(10);
		textField_16.setBounds(137, 509, 109, 20);
		getContentPane().add(textField_16);

		textField_17 = new JTextField();
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_17.setEnabled(false);
		textField_17.setColumns(10);
		textField_17.setBounds(384, 509, 115, 20);
		getContentPane().add(textField_17);

		JLabel label_26 = new JLabel("ediPartyName");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_26.setBounds(288, 508, 103, 21);
		getContentPane().add(label_26);

		JLabel label_27 = new JLabel("IPAddress");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_27.setBounds(25, 534, 88, 20);
		getContentPane().add(label_27);

		textField_18 = new JTextField();
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_18.setEnabled(false);
		textField_18.setColumns(10);
		textField_18.setBounds(137, 534, 109, 20);
		getContentPane().add(textField_18);

		JLabel label_28 = new JLabel("registered ID");
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_28.setBounds(288, 534, 94, 20);
		getContentPane().add(label_28);

		textField_19 = new JTextField();
		textField_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_19.setEnabled(false);
		textField_19.setColumns(10);
		textField_19.setBounds(384, 534, 115, 20);
		getContentPane().add(textField_19);

		JLabel label_29 = new JLabel("uniformResourceIdentifier");
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_29.setBounds(25, 560, 164, 21);
		getContentPane().add(label_29);

		textField_20 = new JTextField();
		textField_20.setEnabled(false);
		textField_20.setColumns(10);
		textField_20.setBounds(192, 562, 307, 20);
		getContentPane().add(textField_20);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClose.setActionCommand("Cancel");
		btnClose.setBounds(192, 624, 134, 21);
		getContentPane().add(btnClose);

		JLabel label_30 = new JLabel(
				"----------------------------------Extensions-------------------------------------");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_30.setBounds(10, 277, 507, 21);
		getContentPane().add(label_30);

		JLabel label_19 = new JLabel(
				"----------------------------------User Info--------------------------------------");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_19.setBounds(10, 117, 507, 21);
		getContentPane().add(label_19);

		JLabel lblBasicConstraints = new JLabel("Basic Constraints:");
		lblBasicConstraints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBasicConstraints.setBounds(25, 307, 474, 31);
		getContentPane().add(lblBasicConstraints);
        if(kpi.getBci() == null) lblBasicConstraints.setText("Basic Constraints: No Basic Constraints");
        else lblBasicConstraints.setText(kpi.getBci().toString());
        

		JLabel lblIssuerAlternativeNames = new JLabel(
				"Issuer Alternative Names:");
		lblIssuerAlternativeNames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIssuerAlternativeNames.setBounds(25, 424, 164, 21);
		getContentPane().add(lblIssuerAlternativeNames);
		if (kpi.getIani() != null) {
			textField_12.setText(kpi.getIani().getOtherName());
			textField_13.setText(kpi.getIani().getRfc822Name());
			textField_16.setText(kpi.getIani().getDirectoryName());
			textField_18.setText(kpi.getIani().getiPAddress());
			textField_20.setText(kpi.getIani().getUniformResourceIdentifier());
			textField_14.setText(kpi.getIani().getdNSName());
			textField_15.setText(kpi.getIani().getX400Address());
			textField_17.setText(kpi.getIani().getEdiPartyName());
			textField_19.setText(kpi.getIani().getRegisteredID());
		}
		textField_3.setText(kpi.getAlias());
		textField.setText(kpi.getKeySize() + "");
		textField_1.setText(kpi.getValidity() + "");
		textField_4.setText(kpi.getCN());
		textField_6.setText(kpi.getO());
		textField_8.setText(kpi.getC());
		textField_10.setText(kpi.getE());
		textField_5.setText(kpi.getOU());
		textField_7.setText(kpi.getL());
		textField_9.setText(kpi.getST());
		textField_3.setDisabledTextColor(Color.BLACK);
		textField.setDisabledTextColor(Color.BLACK);
		textField_1.setDisabledTextColor(Color.BLACK);
		textField_2.setDisabledTextColor(Color.BLACK);
		textField_4.setDisabledTextColor(Color.BLACK);
		textField_6.setDisabledTextColor(Color.BLACK);
		textField_8.setDisabledTextColor(Color.BLACK);
		textField_10.setDisabledTextColor(Color.BLACK);
		textField_5.setDisabledTextColor(Color.BLACK);
		textField_7.setDisabledTextColor(Color.BLACK);
		textField_9.setDisabledTextColor(Color.BLACK);
		textField_12.setDisabledTextColor(Color.BLACK);
		textField_13.setDisabledTextColor(Color.BLACK);
		textField_16.setDisabledTextColor(Color.BLACK);
		textField_18.setDisabledTextColor(Color.BLACK);
		textField_20.setDisabledTextColor(Color.BLACK);
		textField_14.setDisabledTextColor(Color.BLACK);
		textField_15.setDisabledTextColor(Color.BLACK);
		textField_17.setDisabledTextColor(Color.BLACK);
		textField_19.setDisabledTextColor(Color.BLACK);
		
		txtV = new JTextField();
		txtV.setEditable(false);
		txtV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtV.setText("v3");
		txtV.setBounds(390, 23, 109, 20);
		getContentPane().add(txtV);
		txtV.setColumns(10);
		
		JTextArea txtrKeyusage = new JTextArea();
		txtrKeyusage.setEditable(false);
		txtrKeyusage.setBounds(25, 362, 457, 62);
		getContentPane().add(txtrKeyusage);
		
		JLabel lblKeyUsage = new JLabel("Key Usage:");
		lblKeyUsage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKeyUsage.setBounds(25, 331, 474, 31);
		getContentPane().add(lblKeyUsage);
		
		JLabel lblSigned = new JLabel("");
		lblSigned.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSigned.setBounds(25, 592, 164, 21);
		getContentPane().add(lblSigned);
		lblSigned.setText("IsSigned: " + kpi.isSigned());
		
		JLabel lblSignatureAlgorithmName = new JLabel("Signature Algorithm Name:");
		lblSignatureAlgorithmName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSignatureAlgorithmName.setBounds(133, 592, 181, 21);
		getContentPane().add(lblSignatureAlgorithmName);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(324, 592, 175, 22);
		getContentPane().add(textField_11);
		textField_11.setColumns(10);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		if(kpi.getKui() == null) txtrKeyusage.setText("No Key Usage Extension");
        else txtrKeyusage.setText(kpi.getKui().toString());
		if(kpi.isSigned()){
			textField_11.setText(kpi.getSigAlg());
		}
	}
}

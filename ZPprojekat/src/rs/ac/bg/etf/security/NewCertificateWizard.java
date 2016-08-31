package rs.ac.bg.etf.security;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Random;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class NewCertificateWizard extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textKeySize;
	private JTextField textValidfrom;
	private JTextField textField;
	private JTextField textCN;
	private JTextField textOU;
	private JTextField textO;
	private JTextField textL;
	private JTextField textC;
	private JTextField textST;
	private JTextField textE;
	private JTextField textAlias;
	private X509CertificateBuilder father;
	private KeyPairInfo kpi = new KeyPairInfo();
	private java.util.List<JTextField> fieldList = new java.util.ArrayList<JTextField>();
	private JTextField textPathLen;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public NewCertificateWizard(X509CertificateBuilder xx) {
		father = xx;
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("New Certificate Wizard");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		setBounds(100, 100, 533, 712);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);

		JLabel lblKeySize = new JLabel("Key size:");
		lblKeySize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKeySize.setBounds(10, 49, 69, 21);
		contentPanel.add(lblKeySize);

		textKeySize = new JTextField();

		textKeySize
				.setToolTipText("<html>\r\nRSA claims that 1024-bit keys are likely to become crackable some <br>\r\ntime between 2006 and 2010 and that 2048-bit keys are sufficient <br>\r\nuntil 2030. An RSA key length of 3072 bits should be used if security <br>\r\nis required beyond 2030.\r\n</html>");
		textKeySize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textKeySize.setBounds(67, 49, 164, 21);
		contentPanel.add(textKeySize);
		textKeySize.setColumns(10);

		JLabel lblKeysizeerror = new JLabel("");
		lblKeysizeerror.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKeysizeerror.setForeground(Color.RED);
		lblKeysizeerror.setBounds(67, 81, 164, 21);
		contentPanel.add(lblKeysizeerror);

		JLabel lblCertificateVersion = new JLabel("Certificate version:");
		lblCertificateVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCertificateVersion.setBounds(252, 17, 124, 21);
		contentPanel.add(lblCertificateVersion);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setToolTipText("Currently the version can  only be v3.");
		comboBox.setBounds(386, 19, 69, 20);
		comboBox.addItem("3");
		contentPanel.add(comboBox);

		JLabel lblValidFrom = new JLabel("Validity period:");
		lblValidFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValidFrom.setBounds(258, 49, 98, 21);
		contentPanel.add(lblValidFrom);

		textValidfrom = new JTextField();
		textValidfrom
				.setToolTipText("<html>\r\nEnter a number of days that this certificate is valid for.\r\n</html>");

		textValidfrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textValidfrom.setBounds(349, 49, 135, 21);
		contentPanel.add(textValidfrom);
		textValidfrom.setColumns(10);

		JLabel lblSerialNumber = new JLabel("Serial number:");
		lblSerialNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerialNumber.setBounds(10, 113, 98, 21);
		contentPanel.add(lblSerialNumber);

		textField = new JTextField();
		textField
				.setToolTipText("<html>\r\nSerial number is randomly generated<br>\r\nif you want to enter it manually please <br>\r\ncontact the administrator.\r\n</html>");
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(114, 115, 370, 21);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblUserInfo = new JLabel(
				"----------------------------------User Info--------------------------------------");
		lblUserInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserInfo.setBounds(10, 145, 507, 21);
		contentPanel.add(lblUserInfo);

		JLabel lblCommonName = new JLabel("Common Name:");
		lblCommonName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCommonName.setBounds(10, 177, 111, 21);
		contentPanel.add(lblCommonName);

		textCN = new JTextField();
		textCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCN.setBounds(123, 177, 98, 21);
		contentPanel.add(textCN);
		textCN.setColumns(10);

		JLabel lblOrganizationalUnit = new JLabel("Organizational Unit:");
		lblOrganizationalUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrganizationalUnit.setBounds(244, 178, 133, 19);
		contentPanel.add(lblOrganizationalUnit);

		textOU = new JTextField();
		textOU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textOU.setBounds(386, 177, 98, 21);
		contentPanel.add(textOU);
		textOU.setColumns(10);

		JLabel labelO = new JLabel("Organization:");
		labelO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelO.setBounds(10, 209, 111, 21);
		contentPanel.add(labelO);

		textO = new JTextField();
		textO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textO.setColumns(10);
		textO.setBounds(123, 209, 98, 21);
		contentPanel.add(textO);

		JLabel labelL = new JLabel("Localization:");
		labelL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelL.setBounds(244, 210, 133, 19);
		contentPanel.add(labelL);

		textL = new JTextField();
		textL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textL.setColumns(10);
		textL.setBounds(386, 209, 98, 21);
		contentPanel.add(textL);

		JLabel lblCountryName_1 = new JLabel("Country Name:");
		lblCountryName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountryName_1.setBounds(10, 241, 111, 21);
		contentPanel.add(lblCountryName_1);

		textC = new JTextField();
		textC.setText("RS");
		textC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textC.setColumns(10);
		textC.setBounds(123, 241, 98, 21);
		contentPanel.add(textC);

		JLabel lblStateOrProvince = new JLabel("State/Province Name:");
		lblStateOrProvince.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStateOrProvince.setBounds(244, 242, 145, 19);
		contentPanel.add(lblStateOrProvince);

		textST = new JTextField();
		textST.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textST.setColumns(10);
		textST.setBounds(386, 241, 98, 21);
		contentPanel.add(textST);

		JLabel lblCountryName = new JLabel("E-mail:");
		lblCountryName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountryName.setBounds(10, 273, 111, 21);
		contentPanel.add(lblCountryName);

		textE = new JTextField();
		textE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textE.setColumns(10);
		textE.setBounds(123, 273, 163, 21);
		contentPanel.add(textE);

		JLabel lblextensions = new JLabel(
				"----------------------------------Extensions-------------------------------------");
		lblextensions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblextensions.setBounds(10, 305, 507, 21);
		contentPanel.add(lblextensions);

		JLabel lblKeyPairAlias = new JLabel("Alias:");
		lblKeyPairAlias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKeyPairAlias.setBounds(10, 17, 51, 21);
		contentPanel.add(lblKeyPairAlias);

		textAlias = new JTextField();
		textAlias
				.setToolTipText("<html>\r\nSet a name for new pair of keys <br>\r\nso you can easily identify it later.\r\n</html>");
		textAlias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAlias.setColumns(10);
		textAlias.setBounds(50, 17, 181, 21);
		contentPanel.add(textAlias);

		JLabel lblValidityError = new JLabel("");
		lblValidityError.setForeground(Color.RED);
		lblValidityError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblValidityError.setBounds(349, 81, 135, 21);
		contentPanel.add(lblValidityError);

		kpi.setSerialNumber(new BigInteger(256, new Random()));
		textField.setText("" + kpi.getSerialNumber());

		JLabel lblNoteAllFields = new JLabel("* required fileds");
		lblNoteAllFields.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoteAllFields.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoteAllFields.setBounds(303, 272, 181, 21);
		contentPanel.add(lblNoteAllFields);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("CREATE");
		okButton.setEnabled(false);

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				father.setKp(null);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		fieldList.add(textAlias);
		fieldList.add(textCN);
		fieldList.add(textKeySize);
		fieldList.add(textValidfrom);

		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(233, 49, 20, 21);
		contentPanel.add(lblNewLabel);

		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(486, 49, 20, 21);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(233, 17, 20, 21);
		contentPanel.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(222, 177, 20, 21);
		contentPanel.add(label_2);

		JCheckBox chckbxCertificateAuthority = new JCheckBox("CA");
		chckbxCertificateAuthority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxCertificateAuthority.isSelected()) {
					textPathLen.setEnabled(true);
				} else {
					textPathLen.setEnabled(false);
				}
			}
		});

		chckbxCertificateAuthority.setEnabled(false);
		chckbxCertificateAuthority.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxCertificateAuthority.setBounds(28, 373, 51, 23);
		contentPanel.add(chckbxCertificateAuthority);

		JLabel lblPathLength = new JLabel("Path length:");
		lblPathLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPathLength.setBounds(28, 398, 81, 24);
		contentPanel.add(lblPathLength);

		textPathLen = new JTextField();
		textPathLen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPathLen.setEnabled(false);
		textPathLen.setBounds(115, 400, 40, 19);
		contentPanel.add(textPathLen);
		textPathLen.setColumns(10);

		JCheckBox chckbxCritical = new JCheckBox("critical");
		chckbxCritical.setEnabled(false);
		chckbxCritical.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCritical.setBounds(28, 427, 97, 23);
		contentPanel.add(chckbxCritical);

		JCheckBox chckbxBasicConstraints = new JCheckBox("Basic constraints:");
		chckbxBasicConstraints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxBasicConstraints.isSelected()) {
					chckbxCertificateAuthority.setEnabled(true);
					chckbxCritical.setEnabled(true);
				} else {
					chckbxCertificateAuthority.setEnabled(false);
					chckbxCritical.setEnabled(false);
					textPathLen.setEnabled(false);
				}

			}
		});
		chckbxBasicConstraints.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxBasicConstraints.setBounds(10, 341, 164, 23);
		contentPanel.add(chckbxBasicConstraints);

		JCheckBox chckbxKeyUsage = new JCheckBox("Key Usage:");

		chckbxKeyUsage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxKeyUsage.setBounds(207, 341, 145, 23);
		contentPanel.add(chckbxKeyUsage);

		JCheckBox cbDS = new JCheckBox("digital signature ");
		cbDS.setEnabled(false);
		cbDS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDS.setBounds(233, 367, 124, 23);
		contentPanel.add(cbDS);

		JCheckBox cbNR = new JCheckBox("non repudiation");
		cbNR.setEnabled(false);
		cbNR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbNR.setBounds(233, 393, 124, 23);
		contentPanel.add(cbNR);

		JCheckBox cbKE = new JCheckBox("key encipherment");
		cbKE.setEnabled(false);
		cbKE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbKE.setBounds(361, 367, 136, 23);
		contentPanel.add(cbKE);

		JCheckBox cbDE = new JCheckBox("data encipherment");
		cbDE.setEnabled(false);
		cbDE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDE.setBounds(361, 393, 145, 23);
		contentPanel.add(cbDE);

		JCheckBox cbKA = new JCheckBox("key agreement");
		cbKA.setEnabled(false);
		cbKA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbKA.setBounds(233, 419, 124, 23);
		contentPanel.add(cbKA);

		JCheckBox cbKCS = new JCheckBox("key cert sign");
		cbKCS.setEnabled(false);
		cbKCS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbKCS.setBounds(361, 419, 105, 23);
		contentPanel.add(cbKCS);

		JCheckBox cbCRLS = new JCheckBox("CRL sign");
		cbCRLS.setEnabled(false);
		cbCRLS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCRLS.setBounds(233, 445, 97, 23);
		contentPanel.add(cbCRLS);

		JCheckBox cbEO = new JCheckBox("encipher only");

		cbEO.setEnabled(false);
		cbEO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbEO.setBounds(361, 445, 111, 23);
		contentPanel.add(cbEO);

		JCheckBox cbDO = new JCheckBox("decipher only");
		cbDO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbDO.isSelected())
					cbEO.setEnabled(false);
				else
					cbEO.setEnabled(true);
			}
		});
		cbDO.setEnabled(false);
		cbDO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbDO.setBounds(361, 471, 111, 23);
		contentPanel.add(cbDO);

		JCheckBox chckbxIssuerAlternativeNames = new JCheckBox(
				"Issuer Alternative Names:");

		chckbxIssuerAlternativeNames
				.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxIssuerAlternativeNames.setBounds(10, 475, 221, 23);
		contentPanel.add(chckbxIssuerAlternativeNames);

		JLabel lblOthername = new JLabel("otherName");
		lblOthername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOthername.setBounds(33, 512, 75, 14);
		contentPanel.add(lblOthername);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(145, 509, 111, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblRfcname = new JLabel("rfc822Name");
		lblRfcname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRfcname.setBounds(33, 540, 75, 14);
		contentPanel.add(lblRfcname);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(145, 537, 111, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblDnsname = new JLabel("DNS name");
		lblDnsname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDnsname.setBounds(279, 509, 73, 14);
		contentPanel.add(lblDnsname);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setBounds(369, 509, 115, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblXaddress = new JLabel("x400Address");
		lblXaddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXaddress.setBounds(279, 538, 91, 19);
		contentPanel.add(lblXaddress);

		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setBounds(369, 537, 115, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblDirectoryname = new JLabel("directoryName");
		lblDirectoryname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDirectoryname.setBounds(33, 565, 105, 17);
		contentPanel.add(lblDirectoryname);

		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		lblDirectoryname.setLabelFor(textField_5);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setBounds(145, 562, 111, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setBounds(369, 562, 115, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ediPartyName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(273, 561, 103, 21);
		contentPanel.add(lblNewLabel_1);

		JLabel lblIpaddress = new JLabel("IPAddress");
		lblIpaddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIpaddress.setBounds(33, 587, 88, 20);
		contentPanel.add(lblIpaddress);

		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setBounds(145, 587, 111, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblRegisteredId = new JLabel("registered ID");
		lblRegisteredId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegisteredId.setBounds(279, 587, 88, 20);
		contentPanel.add(lblRegisteredId);

		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(369, 587, 115, 20);
		contentPanel.add(textField_8);

		JLabel lblUniformresourceidentifier = new JLabel(
				"uniformResourceIdentifier");
		lblUniformresourceidentifier
				.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUniformresourceidentifier.setBounds(33, 613, 164, 21);
		contentPanel.add(lblUniformresourceidentifier);

		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setBounds(200, 615, 284, 20);
		contentPanel.add(textField_9);
		textField_9.setColumns(10);

		textKeySize.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					int p = Integer.parseInt(textKeySize.getText());
					if (p < 512) {
						lblKeysizeerror
								.setText("Key must be at least 512 bits long");
						okButton.setEnabled(false);
					} else {
						lblKeysizeerror.setText("");
					}
				} catch (NumberFormatException e1) {
					lblKeysizeerror
							.setText("Key size can contain only numbers");
				}
			}
		});

		textValidfrom.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					Integer.parseInt(textValidfrom.getText());
					lblValidityError.setText("");
				} catch (NumberFormatException ex) {
					lblValidityError.setText("Must be an integer");
					okButton.setEnabled(false);
				}
			}
		});

		class DocumentListenerImpl implements DocumentListener {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

				checkFieldsFull();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

				checkFieldsFull();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

				checkFieldsFull();
			}

			private void checkFieldsFull() {
				for (JTextField field : fieldList) {
					if (field.getText().trim().isEmpty()) {
						okButton.setEnabled(false);
						return;
					}
				}
				okButton.setEnabled(true);
			}

		}

		DocumentListener myDocListener = new DocumentListenerImpl();
		for (JTextField field : fieldList) {
			field.getDocument().addDocumentListener(myDocListener);
		}

		chckbxKeyUsage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxKeyUsage.isSelected()) {
					cbCRLS.setEnabled(true);
					cbDE.setEnabled(true);
					if (cbEO.isSelected())
						cbDO.setEnabled(false);
					else
						cbDO.setEnabled(true);
					cbDS.setEnabled(true);
					cbKA.setEnabled(true);
					cbKCS.setEnabled(true);
					cbKE.setEnabled(true);
					cbNR.setEnabled(true);
					if (cbDO.isSelected())
						cbEO.setEnabled(false);
					else
						cbEO.setEnabled(true);
				} else {
					cbCRLS.setEnabled(false);
					cbDE.setEnabled(false);
					cbDO.setEnabled(false);
					cbDS.setEnabled(false);
					cbEO.setEnabled(false);
					cbKA.setEnabled(false);
					cbKCS.setEnabled(false);
					cbKE.setEnabled(false);
					cbNR.setEnabled(false);
				}
			}
		});

		chckbxIssuerAlternativeNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxIssuerAlternativeNames.isSelected()) {
					textField_1.setEnabled(true);
					textField_2.setEnabled(true);
					textField_3.setEnabled(true);
					textField_4.setEnabled(true);
					textField_5.setEnabled(true);
					textField_6.setEnabled(true);
					textField_7.setEnabled(true);
					textField_8.setEnabled(true);
					textField_9.setEnabled(true);
				} else {
					textField_1.setEnabled(false);
					textField_2.setEnabled(false);
					textField_3.setEnabled(false);
					textField_4.setEnabled(false);
					textField_5.setEnabled(false);
					textField_6.setEnabled(false);
					textField_7.setEnabled(false);
					textField_8.setEnabled(false);
					textField_9.setEnabled(false);
				}
			}
		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kpi.setAlias(textAlias.getText());
				kpi.setC(textC.getText());
				kpi.setCN(textCN.getText());
				kpi.setE(textE.getText());
				kpi.setKeySize(Integer.parseInt(textKeySize.getText()));
				kpi.setL(textL.getText());
				kpi.setO(textO.getText());
				kpi.setOU(textOU.getText());
				kpi.setST(textST.getText());
				kpi.setValidity(Long.parseLong(textValidfrom.getText()));
				if (chckbxBasicConstraints.isSelected()) {
					if (chckbxCertificateAuthority.isSelected()) {
						int x = 0;
						try {
							x = Integer.parseInt(textPathLen.getText());
						} catch (NumberFormatException ex) {
							x = 0;
						}
						kpi.setBci(new BasicConstraintsInfo(
								chckbxCertificateAuthority.isSelected(), x,
								chckbxCritical.isSelected()));
					} else
						kpi.setBci(new BasicConstraintsInfo(
								chckbxCertificateAuthority.isSelected(), -1,
								chckbxCritical.isSelected()));
				}
				if (chckbxIssuerAlternativeNames.isSelected()) {
					IssuerAltNameInfo iani = new IssuerAltNameInfo();
					iani.setDirectoryName(textField_5.getText());
					iani.setdNSName(textField_3.getText());
					iani.setEdiPartyName(textField_6.getText());
					iani.setiPAddress(textField_7.getText());
					iani.setOtherName(textField_1.getText());
					iani.setRegisteredID(textField_8.getText());
					iani.setRfc822Name(textField_2.getText());
					iani.setUniformResourceIdentifier(textField_9.getText());
					iani.setX400Address(textField_4.getText());
					kpi.setIani(iani);
				}
				if (chckbxKeyUsage.isSelected()) {
					KeyUsageInfo kui = new KeyUsageInfo();
					kui.setcRLSign(cbCRLS.isSelected());
					kui.setDataEncipherment(cbDE.isSelected());
					kui.setDecipherOnly(cbDO.isSelected());
					kui.setDigitalSignature(cbDS.isSelected());
					kui.setEncipherOnly(cbEO.isSelected());
					kui.setKeyAgreement(cbKA.isSelected());
					kui.setKeyCertSign(cbKCS.isSelected());
					kui.setKeyEncipherment(cbKE.isSelected());
					kui.setNonRepudiation(cbNR.isSelected());
					kpi.setKui(kui);
				}
				father.setKp(kpi);
				dispose();
			}
		});

		cbEO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbEO.isSelected()) {
					cbDO.setEnabled(false);
				} else {
					cbDO.setEnabled(true);
				}
			}
		});
	}
}

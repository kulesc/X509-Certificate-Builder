package rs.ac.bg.etf.security;

import java.awt.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.List;

import javax.swing.event.*;

import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x500.*;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.*;
import org.bouncycastle.jce.provider.*;
import org.bouncycastle.operator.*;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

@SuppressWarnings("deprecation")
public class X509CertificateBuilder {

	private X509CertificateBuilder self;
	private JFrame frmXCertificateBuilder;
	private JDialog wizard;
	private HashMap<String, KeyPairInfo> database;
	private HashMap<String, X509Certificate> certificates;
	private KeyPairInfo kp;
	private DefaultListModel<String> model, model2;
	private KeyPair keyPairCA;
	
	public void setKp(KeyPairInfo kp) {
		this.kp = kp;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					X509CertificateBuilder window = new X509CertificateBuilder();
					window.frmXCertificateBuilder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public X509CertificateBuilder() {

		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		self = this;
		model = new DefaultListModel<>();
		model2 = new DefaultListModel<>();
		frmXCertificateBuilder = new JFrame();
		frmXCertificateBuilder.setTitle("X509 Certificate Builder");
		frmXCertificateBuilder.setResizable(false);
		frmXCertificateBuilder.setBounds(100, 100, 674, 329);
		frmXCertificateBuilder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frmXCertificateBuilder
				.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frmXCertificateBuilder
				.getHeight()) / 2);
		frmXCertificateBuilder.setLocation(x, y);

		JMenuBar menuBar = new JMenuBar();
		frmXCertificateBuilder.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmGenerateNewPair = new JMenuItem(
				"Generate new pair of keys");

		mnFile.add(mntmGenerateNewPair);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
				System.exit(0);
			}
		});

		JMenuItem mntmImportPairOf = new JMenuItem("Import pair of keys");

		mnFile.add(mntmImportPairOf);

		JMenuItem mntmExportWithEncryption = new JMenuItem(
				"Export with encryption");

		mntmExportWithEncryption.setEnabled(false);
		mnFile.add(mntmExportWithEncryption);

		JMenuItem mntmImportEncryptedPair = new JMenuItem(
				"Import encrypted pair of keys");
		mntmImportEncryptedPair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KeyPairInfo kp = decrypt();
				if(kp != null){
				if (database.containsKey(kp.getAlias())) {
					JOptionPane.showMessageDialog(null,
							"KeyPair with same alias already exists!");
				} else {
					database.put(kp.getAlias(), kp);
					model.addElement(kp.getAlias());
				}
			}}
		});
		mnFile.add(mntmImportEncryptedPair);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "\nVersion: 1.0\nProgrammers: Aleksandra Racic, Ivan Kulezic\n" + 
			"Advisor: Zarko Stanisavljevic\nDeveloped at: University of Belgrade, School of Electrical Engineering\n" + 
						"Contact: ivankulezic@gmail.com\nCopyright © 2016", "About X509CertificateBuilder", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		frmXCertificateBuilder.getContentPane().setLayout(null);

		JList<String> list = new JList<>(model);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setBounds(40, 59, 170, 198);
		frmXCertificateBuilder.getContentPane().add(list);

		JLabel lblGeneratedKeyPairs = new JLabel("Generated key pairs");
		lblGeneratedKeyPairs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneratedKeyPairs.setBounds(40, 31, 161, 17);
		frmXCertificateBuilder.getContentPane().add(lblGeneratedKeyPairs);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteFromList(list.getSelectedValue(), list.getSelectedIndex());
			}
		});
		btnRemove.setEnabled(false);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBounds(220, 71, 119, 23);
		frmXCertificateBuilder.getContentPane().add(btnRemove);

		JButton btnViewInfo = new JButton("View Info");

		btnViewInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewInfo.setEnabled(false);
		btnViewInfo.setBounds(220, 105, 119, 23);
		frmXCertificateBuilder.getContentPane().add(btnViewInfo);

		JButton btnExport = new JButton("Export (.p12)");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Security.addProvider(new BouncyCastleProvider());
					KeyPairInfo kpi = database.get(list.getSelectedValue());
					KeyPair pair = kpi.getKeyPair();
					PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
							new X500Name(kpi.formatP12()), kpi.getKeyPair()
									.getPublic());
					JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder(
							"SHA256withRSA");
					ContentSigner signer = csBuilder.build(kpi.getKeyPair()
							.getPrivate());
					org.bouncycastle.pkcs.PKCS10CertificationRequest csr = p10Builder
							.build(signer);
					AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder()
							.find("SHA1withRSA");
					AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder()
							.find(sigAlgId);
					AsymmetricKeyParameter foo = PrivateKeyFactory
							.createKey(pair.getPrivate().getEncoded());
					SubjectPublicKeyInfo keyInfo = SubjectPublicKeyInfo
							.getInstance(pair.getPublic().getEncoded());
					PKCS10CertificationRequest pk10Holder = new PKCS10CertificationRequest(
							csr.getEncoded());
					X509v3CertificateBuilder myCertificateGenerator = new X509v3CertificateBuilder(
							new X500Name(
									"CN=Ivan, O=ETF, OU=RTI, L=Belgrade, ST=Central Serbia, C=RS, EMAILADDRESS=ivankulezic@gmail.com"),
							kpi.getSerialNumber(), kpi.getNotBefore(), kpi
									.getNotAfter(), pk10Holder.getCertificationRequestInfo().getSubject(),
							keyInfo);
					
					Extension keyUsageExt = null;
					Extension basicConExt = null;
					if(kpi.getBci() != null){
						BasicConstraintsInfo bci = kpi.getBci();
						BasicConstraints basic;
						if(bci.getPathLen() >= 0) basic = new BasicConstraints(bci.getPathLen());
						else basic = new BasicConstraints(false);
						basicConExt = new Extension(Extension.basicConstraints, bci.isCritical(), basic.getEncoded());
						myCertificateGenerator.addExtension(basicConExt);
					}
					if(kpi.getKui() != null){
						int x = 0;
						KeyUsageInfo kui = kpi.getKui();
						if(kui.iscRLSign()) x = x | X509KeyUsage.cRLSign;
						if(kui.isDataEncipherment()) x = x | X509KeyUsage.dataEncipherment;
						if(kui.isDecipherOnly()) x = x | X509KeyUsage.decipherOnly;
						if(kui.isDigitalSignature()) x = x | X509KeyUsage.digitalSignature;
						if(kui.isEncipherOnly()) x = x | X509KeyUsage.encipherOnly;
						if(kui.isKeyAgreement()) x = x | X509KeyUsage.keyAgreement;
						if(kui.isKeyCertSign()) x = x | X509KeyUsage.keyCertSign;
						if(kui.isKeyEncipherment()) x = x | X509KeyUsage.keyEncipherment;
						if(kui.isNonRepudiation()) x = x | X509KeyUsage.nonRepudiation;
						X509KeyUsage keyuse = new X509KeyUsage(x);    
						keyUsageExt = new Extension(Extension.keyUsage,kui.isCritical(),keyuse.getEncoded());
						myCertificateGenerator.addExtension(keyUsageExt);
					}
					if(kpi.getIani() != null){
						
						IssuerAltNameInfo iani = kpi.getIani();
						ArrayList<GeneralName> list = new ArrayList<>();
						if(!iani.getOtherName().equals("")) list.add(new GeneralName(GeneralName.otherName, new DERIA5String(iani.getOtherName())));
						if(!iani.getRfc822Name().equals("")) list.add(new GeneralName(GeneralName.rfc822Name, iani.getRfc822Name()));
						if(!iani.getdNSName().equals("")) list.add(new GeneralName(GeneralName.dNSName, iani.getdNSName()));
						if(!iani.getX400Address().equals("")) list.add(new GeneralName(GeneralName.x400Address, new X509Name(iani.getX400Address())));
						if(!iani.getDirectoryName().equals("")) list.add(new GeneralName(GeneralName.directoryName, new X509Name(iani.getDirectoryName())));
						if(!iani.getEdiPartyName().equals("")) list.add(new GeneralName(GeneralName.ediPartyName, new DERIA5String(iani.getEdiPartyName())));
						if(!iani.getUniformResourceIdentifier().equals("")) list.add(new GeneralName(GeneralName.uniformResourceIdentifier, iani.getUniformResourceIdentifier()));
						if(!iani.getiPAddress().equals("")) list.add(new GeneralName(GeneralName.iPAddress, iani.getiPAddress()));
						if(!iani.getRegisteredID().equals("")) list.add(new GeneralName(GeneralName.registeredID, iani.getRegisteredID()));
						
						
						GeneralName[] niz = new GeneralName[list.size()];
						for(int i = 0; i < list.size(); i++){
							niz[i] = list.get(i);

						}
						
						myCertificateGenerator.addExtension(X509Extension.issuerAlternativeName, false, new GeneralNames(niz));
					}
					
					
					
					ContentSigner sigGen = new BcRSAContentSignerBuilder(
							sigAlgId, digAlgId).build(foo);

					X509CertificateHolder holder = myCertificateGenerator
							.build(sigGen);
					X509Certificate s = new JcaX509CertificateConverter().setProvider(new BouncyCastleProvider()).getCertificate(holder);
					JFileChooser jfc = new JFileChooser();
					jfc.setDialogTitle("Select Export Path");
					jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jfc.showOpenDialog(null);
					if (jfc.getSelectedFile() != null) {
						KeyStore ks = KeyStore.getInstance("PKCS12");
						ks.load(null, null);
						ks.setCertificateEntry(kpi.getAlias(), s);
						
						FileOutputStream fos = new FileOutputStream(jfc
								.getSelectedFile()
								+ "/"
								+ kpi.getAlias()
								+ ".p12");
						char[] sif = { 'a', 'd', 'm', 'i', 'n' };
						JPasswordField pf = new JPasswordField();
						int okCxl = JOptionPane.showConfirmDialog(null, pf,
								"Enter Password", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						if (okCxl == JOptionPane.OK_OPTION)
							sif = pf.getPassword();
						Certificate[] niz = new Certificate[1];
						niz[0] = s;
						
						ks.setKeyEntry(kpi.getAlias() + "1", kpi.getKeyPair().getPrivate(), sif, niz);
						ks.store(fos, sif);
						fos.close();
						
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "One or more IssueAlternativeName extension fileds are incorrect!");
				}

			}
		});
		btnExport.setEnabled(false);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExport.setBounds(220, 140, 119, 23);
		frmXCertificateBuilder.getContentPane().add(btnExport);

		JButton btnCreateCsr = new JButton("Create CSR");

		btnCreateCsr.setEnabled(false);
		btnCreateCsr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateCsr.setBounds(220, 171, 119, 23);
		frmXCertificateBuilder.getContentPane().add(btnCreateCsr);

		JList<String> list_1 = new JList<String>(model2);
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setBounds(349, 59, 170, 198);
		frmXCertificateBuilder.getContentPane().add(list_1);

		JLabel lblGeneratedCertificates = new JLabel("Generated Certificates");
		lblGeneratedCertificates.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGeneratedCertificates.setBounds(349, 34, 170, 14);
		frmXCertificateBuilder.getContentPane().add(lblGeneratedCertificates);
		
		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				deleteCert(list_1.getSelectedValue(), list_1.getSelectedIndex());
			}
		});
		btnRemove_1.setEnabled(false);
		btnRemove_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove_1.setBounds(536, 73, 122, 23);
		frmXCertificateBuilder.getContentPane().add(btnRemove_1);
		
		JButton btnExportToBase = new JButton("Export to .cer");
		
		btnExportToBase.setEnabled(false);
		btnExportToBase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExportToBase.setBounds(536, 107, 122, 23);
		frmXCertificateBuilder.getContentPane().add(btnExportToBase);

		File keys = new File("keys");
		if(!keys.exists()){
			try {
				keys.createNewFile();
				FileOutputStream fos = new FileOutputStream(keys);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
				SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
				kpg.initialize(1024, sr);
				KeyPair kp = kpg.generateKeyPair();
				oos.writeObject(kp);
				oos.flush();
				oos.close();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		try (FileInputStream fis = new FileInputStream(keys);
				ObjectInputStream ois = new ObjectInputStream(fis);) {

			keyPairCA = (KeyPair) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		certificates = new HashMap<>();
		File f = new File("certificates");
		if (!f.exists()) {
			try {

				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(new HashMap<>());
				oos.flush();
				oos.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);) {

			certificates = (HashMap<String, X509Certificate>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		database = new HashMap<>();
		File file = new File("keypairs");
		if (!file.exists())
			try {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(new HashMap<>());
				oos.flush();
				oos.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);) {

			database = (HashMap<String, KeyPairInfo>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		frmXCertificateBuilder
				.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmXCertificateBuilder.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				onClose();
				System.exit(0);
			}
		});

		mntmGenerateNewPair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wizard = new NewCertificateWizard(X509CertificateBuilder.this);
				wizard.setVisible(true);
				if (kp != null) {
					try {
						KeyPairGenerator kpg = KeyPairGenerator
								.getInstance("RSA");
						SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
						kpg.initialize(kp.getKeySize(), sr);
						kp.setKeyPair(kpg.generateKeyPair());
					} catch (Exception e) {
						e.printStackTrace();
					}
					KeyPairInfo k = database.get(kp.alias);
					if(k == null){
						database.put(kp.alias, kp);
						model.addElement(kp.getAlias());
					}else{
						JOptionPane.showMessageDialog(null,"Key Pair with same alias alredy exist! Try again.");
					}
					
				}
			}
		});

		for (String key : database.keySet()) {
			model.addElement(key);
		}

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.getSelectedIndex() == -1) {
					btnRemove.setEnabled(false);
					btnViewInfo.setEnabled(false);
					btnExport.setEnabled(false);
					mntmExportWithEncryption.setEnabled(false);
					btnCreateCsr.setEnabled(false);
				} else {
					btnRemove.setEnabled(true);
					btnViewInfo.setEnabled(true);
					btnExport.setEnabled(true);
					mntmExportWithEncryption.setEnabled(true);
					btnCreateCsr.setEnabled(true);
				}
			}
		});

		btnViewInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyPairInfo k = database.get(list.getSelectedValue());
				new CertificatePreview(k).setVisible(true);
			}
		});

		mntmImportPairOf.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select Export Path");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showOpenDialog(null);
				if (jfc.getSelectedFile() != null) {
					try (FileInputStream fis1 = new FileInputStream(jfc
							.getSelectedFile());) {
						KeyStore ks1 = KeyStore.getInstance("PKCS12");
						char[] sif = null;
						JPasswordField pf = new JPasswordField();
						int okCxl = JOptionPane.showConfirmDialog(null, pf,
								"Enter Password", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						if (okCxl == JOptionPane.OK_OPTION) {
							sif = pf.getPassword();
							String certName = jfc.getSelectedFile().getName();
							certName = certName.substring(0,
									certName.length() - 4);
							if (database.containsKey(certName)) {
								JOptionPane
										.showMessageDialog(null,
												"Pair of Keys with this name already exists!");
							}else{
							ks1.load(fis1, sif);
							X509Certificate c = (X509Certificate) ks1.getCertificate(certName);
							KeyPairInfo newKPI = new KeyPairInfo();
							newKPI.setAlias(certName);
							newKPI.setKeySize(0);
							PrivateKey key = (PrivateKey) ks1.getKey(certName + "1", sif);
							
							newKPI.setKeyPair(new KeyPair(c.getPublicKey(), key));
							newKPI.setValidity((c.getNotAfter().getTime() - c.getNotBefore().getTime())/1000/60/60/24);
							newKPI.setNotAfter(c.getNotAfter());
							newKPI.setNotBefore(c.getNotBefore());
							newKPI.setSerialNumber(c.getSerialNumber());
							BasicConstraintsInfo bci = null;
							Set<String> critical = c.getCriticalExtensionOIDs();
							if(critical != null){
							if(critical.contains("2.5.29.19")){
								if(c.getBasicConstraints() < 0) bci = new BasicConstraintsInfo(false, -1, true);
								else bci = new BasicConstraintsInfo(true, c.getBasicConstraints(), true);
							}else{
								if(c.getBasicConstraints() < 0) bci = new BasicConstraintsInfo(false, -1, false);
								else bci = new BasicConstraintsInfo(true, c.getBasicConstraints(), false);
							}}
							newKPI.setBci(bci);
							
							newKPI.setIani(null);
							KeyUsageInfo kui = null;
							if(c.getKeyUsage() != null)kui = new KeyUsageInfo(c.getKeyUsage());
							newKPI.setKui(kui);
							
							String imena = c.getSubjectX500Principal().getName();
							int i = imena.indexOf("STREET=");
							int j = imena.indexOf(",", i);
							String ime = imena.substring(i + 7, j);
							newKPI.setE(ime);
							i = imena.indexOf("C=");
							j = imena.indexOf(",", i);
							ime = imena.substring(i + 2, j);
							newKPI.setC(ime);
							i = imena.indexOf("ST=");
							j = imena.indexOf(",", i);
							ime = imena.substring(i + 3, j);
							newKPI.setST(ime);
							i = imena.indexOf("L=");
							j = imena.indexOf(",", i);
							ime = imena.substring(i + 2, j);
							newKPI.setL(ime);
							i = imena.indexOf("OU=");
							j = imena.indexOf(",", i);
							ime = imena.substring(i + 3, j);
							newKPI.setOU(ime);
							i = imena.indexOf("O=");
							j = imena.indexOf(",", i);
							ime = imena.substring(i + 2, j);
							newKPI.setO(ime);
							i = imena.indexOf("CN=");
							ime = imena.substring(i + 3, imena.length());
							newKPI.setCN(ime);
							Collection<List<?>> altNames = c.getIssuerAlternativeNames();
							IssuerAltNameInfo iani = null;
							if(altNames != null){
								iani = new IssuerAltNameInfo();
								Iterator itr = altNames.iterator();
								while(itr.hasNext()){
									List<?> lst = (List<?>) itr.next();
									Object  typeNo =  lst.get(0);
									Object value = lst.get(1);
									 switch(typeNo.toString()){
									 	case("0"):
									 		iani.setOtherName(value.toString());
									     	break;
									 	case("1"):
									 		iani.setRfc822Name(value.toString());
									 		break;
									 	case("2"):
									 		iani.setdNSName(value.toString());
									 		break;
									 	case("3"):
									 		iani.setX400Address(value.toString());
									 		break;
									 	case("4"):
									 		iani.setDirectoryName(value.toString());
									 		break;
									 	case("5"):
									 		iani.setEdiPartyName(value.toString());
									 		break;
									 	case("6"):
									 		iani.setUniformResourceIdentifier(value.toString());
									 		break;
									 	case("7"):
									 		iani.setiPAddress(value.toString());
									 		break;
									 	case("8"):
									 		iani.setRegisteredID(value.toString());
									 		break;
									 }
								}
							}
							newKPI.setIani(iani);
							database.put(newKPI.getAlias(), newKPI);
							model.addElement(newKPI.getAlias());
						}}
					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(null, "Wrong password!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		mntmExportWithEncryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select Export Path");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.showOpenDialog(null);
				if (jfc.getSelectedFile() != null) {
					String sif = "administratorska";
					JPasswordField pf = new JPasswordField();
					int okCxl = JOptionPane.showConfirmDialog(null, pf,
							"Enter Password for AES encryption",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					if (okCxl == JOptionPane.OK_OPTION)
						sif = pf.getText();
					KeyPairInfo kpi = database.get(list.getSelectedValue());
					encrypt(kpi, sif, jfc.getSelectedFile());
				}
			}
		});

		btnCreateCsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KeyPairInfo kpi = database.get(list.getSelectedValue());
				try {
					PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
							new X500Name(kpi.format()), kpi.getKeyPair()
									.getPublic());
					JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder(
							"SHA256withRSA");
					ContentSigner signer = csBuilder.build(kpi.getKeyPair()
							.getPrivate());
					org.bouncycastle.pkcs.PKCS10CertificationRequest csr = p10Builder
							.build(signer);
					
					new CertificateSignWindow(kpi, csr, self, keyPairCA).setVisible(true);

				} catch (OperatorCreationException e) {
					e.printStackTrace();
				}

			}
		});
		
		for (String key : certificates.keySet()) {
			model2.addElement(key);
		}
		
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(list_1.getSelectedIndex() == -1){
					btnRemove_1.setEnabled(false);
					btnExportToBase.setEnabled(false);
				}else{
					btnRemove_1.setEnabled(true);
					btnExportToBase.setEnabled(true);
				}
			}
		});
		
		btnExportToBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				X509Certificate cert = certificates.get(list_1.getSelectedValue());
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select Export Path");
				jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
				jfc.showOpenDialog(null);
				
				FileOutputStream fos;
				try {
					JTextField pf = new JTextField();
					int okCxl = JOptionPane.showConfirmDialog(null, pf,
							"Enter certificate name:",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (okCxl == JOptionPane.OK_OPTION){
						String name = pf.getText();
					
					File file = new File(jfc.getSelectedFile().getPath() + "/" + name + ".cer");
					fos = new FileOutputStream(file);
					fos.write("-----BEGIN CERTIFICATE-----".getBytes());
					fos.write(org.bouncycastle.util.encoders.Base64.encode(cert.getEncoded()));
					fos.write("-----END CERTIFICATE-----".getBytes());
					
					fos.flush();
					fos.close();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

	private void encrypt(KeyPairInfo kp, String sif, File path) {
		try (FileOutputStream fos = new FileOutputStream(path + "/"
				+ kp.getAlias() + ".aes")) {

			if (sif.length() % 16 != 0) {
				JOptionPane.showMessageDialog(null,
						"Password must be multiplier of 16 bits!");
				return;
			}
			SecretKeySpec sks = new SecretKeySpec(sif.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, sks);
			SealedObject sealedObject = new SealedObject(kp, cipher);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			ObjectOutputStream outputStream = new ObjectOutputStream(cos);
			outputStream.writeObject(sealedObject);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private KeyPairInfo decrypt() {
		KeyPairInfo kp = null;
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select Export Path");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showOpenDialog(null);
		if (jfc.getSelectedFile() != null) {
			String sif = "administratorska";
			JPasswordField pf = new JPasswordField();
			int okCxl = JOptionPane.showConfirmDialog(null, pf,
					"Enter Password for AES decryption",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (okCxl == JOptionPane.OK_OPTION)
				sif = pf.getText();
			if (sif.length() % 16 != 0) {
				JOptionPane.showMessageDialog(null,
						"Password must be multiplier of 16 bits!");
				return null;
			}
			try (FileInputStream fis = new FileInputStream(
					jfc.getSelectedFile())) {
				SecretKeySpec sks = new SecretKeySpec(sif.getBytes(), "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, sks);

				CipherInputStream cipherInputStream = new CipherInputStream(
						new BufferedInputStream(fis), cipher);
				ObjectInputStream inputStream = new ObjectInputStream(
						cipherInputStream);
				SealedObject sealedObject = (SealedObject) inputStream
						.readObject();
				kp = (KeyPairInfo) sealedObject.getObject(cipher);
				inputStream.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Wrong password!");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return kp;
	}

	private void deleteCert(String alias, int idx){
		certificates.remove(alias);
		model2.remove(idx);
	}
	
	private void deleteFromList(String alias, int idx) {
		database.remove(alias);
		model.remove(idx);
	}

	private void onClose() {
		File file = new File("keypairs");
		try (FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(database);
			oos.flush();
		} catch (Exception e) {

		}
		File f = new File("certificates");
		try (FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(certificates);
			oos.flush();
		} catch (Exception e) {

		}
		File keys = new File("keys");
		try (FileOutputStream fos = new FileOutputStream(keys);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(keyPairCA);
			oos.flush();
		} catch (Exception e) {

		}
	}
	
	public void putCert(String alias, X509Certificate c){
		X509Certificate cer = certificates.get(alias);
		if(cer == null){
			certificates.put(alias, c);
			model2.addElement(alias);
		}else{
			JOptionPane.showMessageDialog(null, "Certificate with same name alredy exists, please choose another one!");
		}
		
	}
}

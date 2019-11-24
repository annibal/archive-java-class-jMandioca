//package mandioquito;
//=
//public class asdasd {
//
//	public static byte[] showTelaEscolheImage(
//			File diretorioOrigem, 
//			ImagePanel previewPanel, 
//			JTextField txtCaminhoFoto,
//			Component telaPai,
//			String... extensoes
//		) throws EntradaUsuarioException {
//		
//		JFileChooser telaEscolheFoto = new JFileChooser(diretorioOrigem);
//		telaEscolheFoto.setFileFilter(new FiltroExtensao(extensoes));
//
//		telaEscolheFoto.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//
//		byte[] arrayAux = null;
//
//		int ret = telaEscolheFoto.showSaveDialog(telaPai);
//
//		if (ret == JFileChooser.APPROVE_OPTION) {
//			File fileFoto = telaEscolheFoto.getSelectedFile();
//			txtCaminhoFoto.setText(fileFoto.getAbsolutePath());
//			try {
//				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFoto));
//				arrayAux = new byte[bis.available()];
//				bis.read(arrayAux);
//
//				previewPanel.setImagem(arrayAux);
//				previewPanel.repaint();
//			} catch (IOException e) {
//				throw new EntradaUsuarioException(txtCaminhoFoto, "Não foi possível ler a imagem da foto", e);
//			}
//		}
//		return arrayAux;
//	}
//}

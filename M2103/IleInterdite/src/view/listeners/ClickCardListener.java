package view.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.Controleur;
import view.VuePlateau;
import view.plateau.jeu.pioches.VueCarte;

public class ClickCardListener implements MouseListener {
	
	private VueCarte vueCarte;
	
	public ClickCardListener(VueCarte vueCarte) {
		this.setVueCarte(vueCarte);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		this.getVueCarte().setExtendsWidth(1.5);
//		this.getVueCarte().setExtendsHeight(1.5);
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		this.getVueCarte().setExtendsWidth(1.0);
//		this.getVueCarte().setExtendsHeight(1.0);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public VueCarte getVueCarte() {
		return vueCarte;
	}
	
	private void setVueCarte(VueCarte vueCarte) {
		this.vueCarte = vueCarte;
	}
}

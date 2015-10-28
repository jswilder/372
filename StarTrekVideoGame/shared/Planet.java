
package StarTrekVideoGame.shared;

class Planet {
    private int SX;
    private int SY;
    private int PX;
    private int PY;

    public Planet() {}

    /**
     * Planet constructor that takes in four integers, which are it's location.
     * @param sx
     * @param sy
     * @param px
     * @param py
     */
    public Planet(int sx, int sy, int px, int py) {
        this.SX = sx;
        this.SY = sy;
        this.PX = px;
        this.PY = py;
    }

    //GETTERS AND SETTERS
    
	public int getSX() {
		return SX;
	}

	public void setSX(int sX) {
		SX = sX;
	}

	public int getSY() {
		return SY;
	}

	public void setSY(int sY) {
		SY = sY;
	}

	public int getPX() {
		return PX;
	}

	public void setPX(int pX) {
		PX = pX;
	}

	public int getPY() {
		return PY;
	}

	public void setPY(int pY) {
		PY = pY;
	}
}

package service.websocket.response;

public class PositionResponse {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    int x;
    int y;

    public PositionResponse( ){

    }
}

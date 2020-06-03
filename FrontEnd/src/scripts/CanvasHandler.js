import avatarRed from "../images/Avatars/characterRed.png"
import avatarBlue from "../images/Avatars/characterBlue.png"
import avatarGreen from "../images/Avatars/characterGreen.png"
import avatarPink from "../images/Avatars/characterPink.png"
import avatarOrange from "../images/Avatars/characterOrange.png"
import avatarYellow from "../images/Avatars/characterYellow.png"

import Wall1 from "../images/walls/wall1.png"
import Wall2 from "../images/walls/wall2.png"
import Obstacle from "../images/obstacle.png"


export class CanvasHandler {
    constructor(contextMaze, contextAvatars, dimensions) {
        this.contextMaze = contextMaze;
        this.contextAvatars = contextAvatars;
        this.dimensions = dimensions;

        this.images = {
            avatars: [
                createImage(avatarBlue),
                createImage(avatarGreen),
                createImage(avatarOrange),
                createImage(avatarPink),
                createImage(avatarYellow),
                createImage(avatarRed)
            ],
            walls: [
                createImage(Wall1),
                createImage(Wall2)
            ],
            obstacle: createImage(Obstacle)
        }

        function createImage(url) {
            const image = new Image();
            image.src = url;
            return image;
        };
    }

    drawMaze(maze) {
        const size = maze.length;
        const height = this.dimensions.height;
        const width = this.dimensions.width;
        this.contextMaze.clearRect(0, 0, width, height);

        function randomWall(that) {
            const i = (Math.floor(Math.random() * 2));
            return that.images.walls[1];
        }

        for (let y = 0; y < size; y++) {
            let field = maze[y];
            for (let x = 0; x < size; x++) {
                if (field[x] === 'WALL') {
                    this.contextMaze.drawImage(randomWall(this), (width / size) * x, (height / size) * y, width / size, height / size);
                } else if (field[x] === 'OBSTACLE') {
                    this.contextMaze.drawImage(this.images.obstacle, (width / size) * x, (height / size) * y, width / size, height / size);
                }
            }
        }
    }

    drawAvatar(maze, players, thisUser) {
        const size = maze.length;
        const height = this.dimensions.height;
        const width = this.dimensions.width;

        this.contextAvatars.clearRect(0, 0, width, height);
        for (let i = 0; i < players.length; i++) {
            let player = players[i];
            for (let y = 0; y < size; y++) {
                if (y === player.position[1]) {
                    for (let x = 0; x < size; x++) {
                        if (x === player.position[0]) {
                            let image = this.images.avatars[parseInt(player.avatar)];
                            console.log(player)
                            if (thisUser.playerId === player.playerId) {
                                this.contextAvatars.globalAlpha = 1;
                            } else {
                                this.contextAvatars.globalAlpha = 0.5;
                            }
                            this.contextAvatars.drawImage(image, (width / size) * x, (height / size) * y, width / size, height / size);
                        }
                    }
                }
            }
        }
    }

    removeObstacle(state, dimensions, posX, poxY) {
        const maze = state.maze;

        const size = maze.length;
        const height = dimensions.height;
        const width = dimensions.width;

        for (let y = 0; y < size; y++) {
            if (y === poxY) {
                for (let x = 0; x < size; x++) {
                    if (x === posX) {
                        maze[x][y] = 'OPEN';
                        this.contextMaze.clearRect((width / size) * x, (height / size) * y, width / size, height / size);
                    }
                }
            }
        }
        return maze;
    }
}
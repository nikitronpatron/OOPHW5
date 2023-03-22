import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class RobotMap {
    private final int n;
    private final int m;

    private final Map<UUID, Robot> robots;

    public RobotMap(int n, int m) {
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Robot createRobot(String name, Point position) throws PositionException {
        checkPosition(position);
        
        Robot robot = new Robot(name, position);
        robots.put(robot.id, robot);
        return robot;
    }

    private void checkPosition(Point position) throws PositionException {
        if(!isFree(position)) {
            throw new PositionException("Точка" + position + " занята!");
        }
        if(position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки:" + position);
        }
    }

    private boolean isFree(Point position) {
        return robots.values().stream()
                .map(Robot::getPosition)
                .noneMatch(position::equals);
    }

    public class Robot {
        private final String name;
        private final UUID id;
        private Point position;
        private Direction direction;

        public Robot(String name, Point position) {
            this.name = name;
            this.id = UUID.randomUUID();
            this.position = position;
            this.direction = Direction.TOP;
        }

        public String getName() {
            return name;
        }

        public UUID getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public void move() throws PositionException {
            Point newPosition = switch (direction) {
                case TOP -> new Point(position.getX() - 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case BOTTOM -> new Point(position.getX() + 1, position.getY());
                case LEFT -> new Point(position.getX(), position.getY() - 1);
            };
            
            checkPosition(newPosition);

            position = newPosition;
        }

        public void changeDirection(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s], %s", id.toString(), name.toString(), position.toString());
        }
    }

    public enum Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }
}
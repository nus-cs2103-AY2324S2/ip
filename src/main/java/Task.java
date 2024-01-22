public abstract class Task {
        private String description;
        private boolean marked;
        Task(String description) {
                this.description = description;
                this.marked = false;
        }

        public Task mark() {
                this.marked = true;
                return this;
        }

        public Task unmark() {
                this.marked = false;
                return this;
        }
        @Override
        public String toString() {
                String check = marked ? "X" : " ";
                return String.format("[%s] %s", check, description);
        }
}

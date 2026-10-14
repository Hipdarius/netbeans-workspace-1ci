package exam;
// SUPPLIED: this class owns the one shared queue and simulates one printer.
public class Workshop {
    private JobQueue queue = new JobQueue();
    private PrintJob active;
    private int completed;
    public JobQueue getQueue() { return queue; }
    public PrintJob getActive() { return active; }
    public int getCompleted() { return completed; }
    public void startNext() { if(active==null) active=queue.pop(); }
    public void tick() {
        if(active==null) return;
        active.workOneMinute();
        if(active.getRemaining()==0) { active=null; completed++; }
    }
    public void reset() { queue.clear(); active=null; completed=0; }
    public void loadExample() {
        reset();
        queue.add(new PrintJob("A",20));
        queue.add(new UrgentJob("B",10));
        queue.add(new PrintJob("C",30));
        queue.add(new UrgentJob("D",15));
        queue.add(new UrgentJob("E",10));
        queue.add(new PrintJob("F",25));
    }
}

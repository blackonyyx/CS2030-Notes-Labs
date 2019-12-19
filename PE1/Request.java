class Request{
    public int req;
    public int pax;
    public int time;

    public Request(int r, int p, int t){
        this.req = r;
        this.pax = p;
        this.time = t;
    }
    public int getDist(){
        return this.req;
    }
    public int getTime(){
        return this.time;
    }
    @Override 
    public String toString(){
        return req +"km for "+ pax+"pax @ " + time+"hrs";
    }
}

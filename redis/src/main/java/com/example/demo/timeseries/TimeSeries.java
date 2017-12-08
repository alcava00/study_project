package com.example.demo.timeseries;

public class TimeSeries {
    private String nameSpace;

    public enum units {
        second(1), minitus(60), hour(60 * 60), day(60 * 60 * 24);
        private int msec = 0;
        units(int second) {
            this.msec = second*1000;
        }

        public int getMsec() {
            return msec;
        }
    }

    public enum granularities {
        sec("1sec", units.hour.msec * 2, units.second.msec),
        min("1min", units.day.msec * 7, units.minitus.msec),
        hour("1hour", units.day.msec * 60, units.hour.msec),
        day("1day", 0, units.day.msec);
        private String name;
        private int ttl;
        private int duration;

        granularities(String name, int ttl, int duration) {
            this.name = name;
            this.ttl = ttl;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}

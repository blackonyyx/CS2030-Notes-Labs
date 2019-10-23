Statistics stats = new Statistics()
stats.serveOneCustomer()
stats.serveOneCustomer().looseOneCustomer()
stats.recordWaitingTime(10.0).serveOneCustomer().looseOneCustomer()
stats.recordWaitingTime(10.0).serveOneCustomer().looseOneCustomer().recordWaitingTime(2.0).serveOneCustomer()
/exit

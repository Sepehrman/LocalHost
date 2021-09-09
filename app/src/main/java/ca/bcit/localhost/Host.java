package ca.bcit.localhost;

public class Host {

    private String address, name, phoneNumber;
    private HostType hostType;
    private double lattitude, longitude;

    public Host(String address, String name, String phoneNumber, HostType hostType, double lattitude, double longitude) {
        this.address = address;
        this.name = name;
        this.hostType = hostType;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {return longitude;}

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public HostType getHostType() {
        return hostType;
    }



}

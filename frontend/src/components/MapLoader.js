import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";
import Map from "./Map";

function MapLoader() {
    const { isLoaded } = useLoadScript({
        googleMapsApiKey: "AIzaSyBWr99TVhQisqs0AhMGZp5JMW5LUZ_U1PA",
      });
    
      if (!isLoaded) return <div>Loading...</div>;
      return <Map />;
}

export default MapLoader
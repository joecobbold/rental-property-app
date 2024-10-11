

//<!--DELETE LATER: NOT USING AT THE MOMENT-->



const allProperties = [
  {
      propertyId: 2,
      address: "456 Oak Ave",
      city: "Sometown",
      state: "NY",
      zipCode: "67890",
      rentPrice: 1800.0,
      bedrooms: 4.0,
      bathrooms: 3.0,
      squareFeet: 2000.0,
      available: true,
      basement: true,
      description: "Spacious house with basement",
      imageUrl: "/propertyImages/1602.png" 
  },
  {
      propertyId: 3,
      address: "789 Maple Blvd",
      city: "Anothercity",
      state: "TX",
      zipCode: "23456",
      rentPrice: 1200.0,
      bedrooms: 2.0,
      bathrooms: 1.0,
      squareFeet: 1000.0,
      available: true,
      basement: false,
      description: "Modern apartment near downtown",
      imageUrl: "/propertyImages/2.png"
  },
  {
      propertyId: 4,
      address: "321 Pine Dr",
      city: "Yourtown",
      state: "FL",
      zipCode: "78901",
      rentPrice: 2200.0,
      bedrooms: 3.0,
      bathrooms: 2.0,
      squareFeet: 1800.0,
      available: true,
      basement: true,
      description: "Renovated townhouse with garage",
      imageUrl: "/propertyImages/3.jpg"
  },
  {
      propertyId: 5,
      address: "654 Elm Ln",
      city: "Somewhere",
      state: "WA",
      zipCode: "34567",
      rentPrice: 1700.0,
      bedrooms: 3.0,
      bathrooms: 2.0,
      squareFeet: 1600.0,
      available: true,
      basement: false,
      description: "Corner lot with garden space",
      imageUrl: "/propertyImages/4.jpg"
  },
  {
      propertyId: 6,
      address: "987 Cedar Rd",
      city: "Nowhere",
      state: "OR",
      zipCode: "45678",
      rentPrice: 1900.0,
      bedrooms: 4.0,
      bathrooms: 3.0,
      squareFeet: 2200.0,
      available: true,
      basement: true,
      description: "Ranch style home with mountain view",
      imageUrl: "/propertyImages/5.jpg"
  },
  {
      propertyId: 7,
      address: "210 Oakwood Ave",
      city: "Anywhere",
      state: "IL",
      zipCode: "56789",
      rentPrice: 1300.0,
      bedrooms: 2.0,
      bathrooms: 1.0,
      squareFeet: 1100.0,
      available: true,
      basement: false,
      description: "Apartment in a quiet neighborhood",
      imageUrl: "/propertyImages/6.jpg"
  },
  {
      propertyId: 8,
      address: "543 Birch St",
      city: "Everytown",
      state: "MA",
      zipCode: "89012",
      rentPrice: 1600.0,
      bedrooms: 3.0,
      bathrooms: 2.0,
      squareFeet: 1700.0,
      available: true,
      basement: true,
      description: "Duplex with fenced backyard",
      imageUrl: "/propertyImages/7.png"
  },
  {
      propertyId: 9,
      address: "876 Spruce Ave",
      city: "Noway",
      state: "PA",
      zipCode: "90123",
      rentPrice: 2000.0,
      bedrooms: 4.0,
      bathrooms: 3.0,
      squareFeet: 2000.0,
      available: true,
      basement: false,
      description: "New construction with modern amenities",
      imageUrl: "/propertyImages/8.png"
  },
  {
      propertyId: 10,
      address: "109 Willow Dr",
      city: "Smalltown",
      state: "GA",
      zipCode: "34567",
      rentPrice: 1400.0,
      bedrooms: 2.0,
      bathrooms: 1.0,
      squareFeet: 1200.0,
      available: true,
      basement: false,
      description: "Single family home with front porch",
      imageUrl: "/propertyImages/9.png"
  },
  {
      propertyId: 11,
      address: "123 new Property",
      city: "Newtown",
      state: "OH",
      zipCode: "43081",
      rentPrice: 1700.0,
      bedrooms: 7.0,
      bathrooms: 7.0,
      squareFeet: 1700.0,
      available: true,
      basement: false,
      description: "Old and preserved Condo in German Village",
      imageUrl: "/propertyImages/10.png"
  },
  {
      propertyId: 15,
      address: "123 Main St",
      city: "Anytown",
      state: "CA",
      zipCode: "12345",
      rentPrice: 1500.0,
      bedrooms: 3.0,
      bathrooms: 2.0,
      squareFeet: 1500.0,
      available: true,
      basement: false,
      description: "Cozy family home with backyard",
      imageUrl: "/propertyImages/11.jpg"
  },
  {
      propertyId: 16,
      address: "123 Main St",
      city: "Anytown",
      state: "CA",
      zipCode: "12345",
      rentPrice: 1500.0,
      bedrooms: 3.0,
      bathrooms: 2.0,
      squareFeet: 1500.0,
      available: true,
      basement: false,
      description: "Cozy family home with backyard",
      imageUrl: "/propertyImages/12.jpg"
  }
];


const ResourceService = {
  // Function to get all properties
  getAllProperties() {
    return allProperties;
  }
};

export default ResourceService;

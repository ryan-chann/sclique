export interface EventFeeProps {
  type: string;
  price: number;
}

export interface EventSessionProps {
  session: string;
}

export interface EventListingProps {
  id: string;
  title: string;
  venue: string;
  eventFees: { price: number }[];
  eventSessions: { session: string }[];
  imageDataBase64: string;
  mimeType: string;
}

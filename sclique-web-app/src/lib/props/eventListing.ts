export interface EventFeeProps {
  type: string;
  price: number;
}

export interface EventSessionProps {
  session: string;
}

export interface EventListingProps {
  title: string;
  venue: string;
  eventFees: EventFeeProps[];
  eventSessions: EventSessionProps[];
  imageDataBase64?: string;
  mimeType?: string;
}

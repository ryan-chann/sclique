import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    domains: ['dummyimage.com'],
  },
  allowedDevOrigins: ['http://192.168.101.161:3000'],
};

export default nextConfig;

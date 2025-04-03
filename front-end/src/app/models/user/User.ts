import { Image } from "./image";
import { Role } from "./role";

import { Document } from "./document";

export class User {
    id: number;
    displayName: string;
    email: string;
    roles: string[];
}
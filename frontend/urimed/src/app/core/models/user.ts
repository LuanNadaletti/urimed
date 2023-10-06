export class User {
  id: number;
  username: string;
  email: string;
  profilePicture: string;

  constructor(
    id: number,
    username: string,
    email: string,
    profilePicture: string
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.profilePicture = profilePicture;
  }
}

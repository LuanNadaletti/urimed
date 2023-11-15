export interface MenuItem {
    label: string;
    destination?: string;
    children?: MenuItem[];
    icon: string;
}
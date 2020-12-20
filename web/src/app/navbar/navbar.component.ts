import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
/**
 * NavbarComponent serves as the main navigation inside the app
 *
 * Provides access to navigation, account settings and a search.
 */
export class NavbarComponent {
  /** brand shows the company/application name in the navbar */
  @Input() brand: string;

  /**
   * Initializes the navbar and sets a default brand
   */
  constructor() {
    this.brand = 'Brand';
  }
}

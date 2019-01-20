import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../shared/profile.service';
import { Profile } from '../shared/profile.module';
import { MatDialog, Sort } from '@angular/material';
import { ProfileDialogComponent } from '../profile-dialog/profile-dialog.component';

const PROFILE_PAGE_ITEMS_SIZE: number = 10;
const PROFILE_PAGE_START_NUMBER: number = 1;

@Component({
  selector: 'app-profile-list',
  templateUrl: './profile-list.component.html',
  styleUrls: ['./profile-list.component.css']
})
export class ProfileListComponent implements OnInit {

  displayedColumns: string[] = [ 'name', 'coreName', "update", "delete"];
  profiles: Profile[] = [];
  page: number;
  size: number;
  isLoadingResults = true;
  /* TODO: Move it to different class for page later. */
  isLast: boolean;
  isFirst: boolean;
  totalPages: number;
  totalElements: number;


  sortBy: string;
  order: string;

  constructor(private profileService: ProfileService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.page = PROFILE_PAGE_START_NUMBER;
    this.size = PROFILE_PAGE_ITEMS_SIZE
    this.sortBy = "unsorted";
    this.order = "asc";
    this.getProfiles(this.page, this.size);
  }

  getProfiles(page: number, size: number): void {
    this.profileService.getProfilesPageSorted(page - 1, size, this.sortBy, this.order)
      .subscribe(pages => {
          this.profiles = pages.content;
          this.isLast = pages.last;
          this.isFirst = pages.first;
          this.totalPages = pages.totalPages;
          this.totalElements = pages.totalElements;
          this.isLoadingResults = false;
      }, err => {
        this.isLoadingResults = false;
      })
  }

  openDialog(profile: Profile): void {
    let dialogRef = this.dialog.open(ProfileDialogComponent, {
      data: profile
    });
    dialogRef.afterClosed().subscribe(result => {
      if (profile == null) {
        this.profileService.addProfile(result).subscribe(data => {
          this.getProfiles(this.page, this.size);
        });
      } else {
        this.profileService.updateProfile(result).subscribe(data => {
          this.getProfiles(this.page, this.size);
        })
      }
    })
  }

  deleteProfile(profile: Profile): void {
    this.isLoadingResults = true;
    let diagRef = this.profileService.deleteProfile(profile).subscribe(data => {
      this.getProfiles(this.page, this.size);
    });
  }

  goToPage(n: number): void {
    this.page = n;
    this.getProfiles(n, this.size);
  }

  onNext(): void {
    this.page++;
    this.getProfiles(this.page, this.size);
  }

  onPrev(): void {
    this.page--;
    this.getProfiles(this.page, this.size);
  }

  sortProfiles(sort: Sort) {
    const data = this.profiles.slice();
    if (!sort.active || sort.direction === '') {
      this.profiles = data;
    }

    const isAsc = sort.direction === 'asc';
    switch (sort.active) {
      case 'name':     
        this.sortBy = "name";
        this.order = sort.direction;
        break;
      case 'core-name': 
        this.sortBy = "core-name";
        this.order = sort.direction;
        break;
      default:
        this.sortBy = "unsorted";
        this.order = "asc";
        break;
    }
    this.getProfiles(this.page, this.size);
  }
}
